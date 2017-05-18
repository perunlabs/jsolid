package com.perunlabs.jsolid.d3.op;

import static com.perunlabs.jsolid.JSolid.x;
import static com.perunlabs.jsolid.JSolid.y;
import static com.perunlabs.jsolid.JSolid.z;
import static com.perunlabs.jsolid.d1.Angle.degrees;
import static com.perunlabs.jsolid.util.Lists.immutable;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.perunlabs.jsolid.JSolid;
import com.perunlabs.jsolid.d1.Angle;
import com.perunlabs.jsolid.d3.Alignment;
import com.perunlabs.jsolid.d3.Anchor3;
import com.perunlabs.jsolid.d3.Axis;
import com.perunlabs.jsolid.d3.Matrix4;
import com.perunlabs.jsolid.d3.Solid;
import com.perunlabs.jsolid.d3.Vector3;

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

  public Solid add(Solid solid, Anchor3<?>... edges) {
    return add(solid, edgesToAlignments(edges));
  }

  public Solid add(Solid solid, Alignment... alignments) {
    if (sides().isEmpty()) {
      throw new IllegalArgumentException("It is not possible to align against empty Solid.");
    }
    return add(align(solid, alignments));
  }

  public Solid sub(Solid solid) {
    return new SubSolid(this, solid);
  }

  public Solid sub(Solid solid, Anchor3<?>... edges) {
    return sub(solid, edgesToAlignments(edges));
  }

  public Solid sub(Solid solid, Alignment... alignments) {
    return sub(align(solid, alignments));
  }

  public Solid intersect(Solid solid) {
    return new IntersectSolid(this, solid);
  }

  public Solid intersect(Solid solid, Anchor3<?>... edges) {
    return intersect(solid, edgesToAlignments(edges));
  }

  public Solid intersect(Solid solid, Alignment... alignments) {
    return intersect(align(solid, alignments));
  }

  private Solid align(Solid solid, Alignment[] alignments) {
    return new AlignSolid(solid, this, asList(alignments));
  }

  private static Alignment[] edgesToAlignments(Anchor3<?>[] edges) {
    return stream(edges)
        .map(e -> JSolid.align(e))
        .toArray(size -> new Alignment[size]);
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

  public Solid rotate(Axis<?> axis, Angle angle) {
    return apply(axis.rotateMatrix(angle));
  }

  @Deprecated
  public Solid rotate(Axis<?> axis, double angle) {
    return rotate(axis, degrees(angle));
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
