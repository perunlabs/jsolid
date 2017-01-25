package com.mikosik.jsolid.d3.op;

import static com.mikosik.jsolid.util.Lists.immutable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import com.mikosik.jsolid.d3.Alignment;
import com.mikosik.jsolid.d3.Anchor;
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

  public Solid add(Solid solid, Alignment alignment) {
    return add(alignment.align(this, solid));
  }

  public Solid sub(Solid solid) {
    return new SubSolid(this, solid);
  }

  public Solid sub(Solid solid, Alignment alignment) {
    return sub(alignment.align(this, solid));
  }

  public Solid intersect(Solid solid) {
    return new IntersectSolid(this, solid);
  }

  public Solid intersect(Solid solid, Alignment alignment) {
    return intersect(alignment.align(this, solid));
  }

  public Solid move(Vector3 position) {
    return apply(Matrix4.move(position));
  }

  public Solid move(Anchor<?> anchor, double value) {
    return move(anchor.axis.v(value).sub(anchor.vectorIn(this)));
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
}
