package com.mikosik.jsolid.d3.op;

import static com.mikosik.jsolid.JSolid.x;
import static com.mikosik.jsolid.JSolid.y;
import static com.mikosik.jsolid.JSolid.z;
import static com.mikosik.jsolid.util.Lists.immutable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.mikosik.jsolid.JSolid;
import com.mikosik.jsolid.d3.Alignment;
import com.mikosik.jsolid.d3.Anchor3;
import com.mikosik.jsolid.d3.Anchor3.EdgeAnchor3;
import com.mikosik.jsolid.d3.Axis;
import com.mikosik.jsolid.d3.Matrix4;
import com.mikosik.jsolid.d3.Solid;
import com.mikosik.jsolid.d3.Vector3;

import eu.mihosoft.vrl.v3d.Polygon;

public abstract class AbstractSolid implements Solid {
  private volatile List<Polygon> sides;

  protected abstract List<Polygon> calculateSides();

  public List<Polygon> sides() {
    List<Polygon> result = sides;
    if (result == null) {
      synchronized (this) {
        result = sides;
        if (result == null) {
          sides = result = immutable(calculateSides());
        }
      }
    }
    return result;
  }

  public List<Vector3> vertexes() {
    return sides().stream()
        .flatMap(x -> x.vertices.stream())
        .collect(toList());
  }

  public Solid apply(Matrix4 matrix) {
    return new TransformedSolid(this, matrix);
  }

  public Solid add(Solid solid) {
    return new AddSolid(this, solid);
  }

  public Solid add(Solid solid, EdgeAnchor3<?>... edges) {
    return add(align(solid, edges));
  }

  public Solid add(Solid solid, Alignment... alignments) {
    return add(align(solid, alignments));
  }

  public Solid sub(Solid solid) {
    return new SubSolid(this, solid);
  }

  public Solid sub(Solid solid, EdgeAnchor3<?>... edges) {
    return sub(align(solid, edges));
  }

  public Solid sub(Solid solid, Alignment... alignments) {
    return sub(align(solid, alignments));
  }

  public Solid intersect(Solid solid) {
    return new IntersectSolid(this, solid);
  }

  public Solid intersect(Solid solid, EdgeAnchor3<?>... edges) {
    return intersect(align(solid, edges));
  }

  public Solid intersect(Solid solid, Alignment... alignments) {
    return intersect(align(solid, alignments));
  }

  private Solid align(Solid solid, EdgeAnchor3<?>[] edges) {
    for (EdgeAnchor3<?> edge : edges) {
      solid = JSolid.align(edge).align(this, solid);
    }
    return solid;
  }

  private Solid align(Solid solid, Alignment[] alignments) {
    for (Alignment alignment : alignments) {
      solid = alignment.align(this, solid);
    }
    return solid;
  }

  public Solid moveBy(Vector3 shift) {
    return apply(Matrix4.move(shift));
  }

  public Solid moveTo(Anchor3<?> anchor, double position) {
    Vector3 desiredPosition = anchor.axis.v(position);
    Vector3 currentPosition = anchor.vectorIn(this);
    Vector3 shift = desiredPosition.sub(currentPosition);
    return moveBy(shift);
  }

  public Solid rotate(Axis<?> direction, double angle) {
    return apply(direction.rotateMatrix(angle));
  }

  public Solid mirror(Axis<?> direction) {
    return apply(direction.mirrorMatrix());
  }

  public Solid scale(Axis<?> direction, double factor) {
    return apply(direction.scaleMatrix(factor));
  }

  public Solid convexHull() {
    return new ConvexHullSolid(this);
  }

  public Solid mirroredTwins(Vector3 shift) {
    Solid shifted = moveBy(shift);
    return shifted
        .add(shifted.mirror(getOnlyComponent(shift)));
  }

  private static Axis<?> getOnlyComponent(Vector3 shift) {
    int nonZeroComponents = nonZeroToOne(shift.x) + nonZeroToOne(shift.y) + nonZeroToOne(shift.z);
    if (nonZeroComponents != 1) {
      throw new IllegalArgumentException(
          "Shift should contain only one non zero component but is " + shift);
    }
    if (shift.x != 0) {
      return x();
    }
    if (shift.y != 0) {
      return y();
    }
    return z();
  }

  private static int nonZeroToOne(double x) {
    return x == 0 ? 0 : 1;
  }
}
