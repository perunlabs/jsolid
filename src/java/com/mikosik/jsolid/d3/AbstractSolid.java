package com.mikosik.jsolid.d3;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.mikosik.jsolid.JSolid;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Polygon;

public abstract class AbstractSolid implements Solid {
  public abstract List<Polygon> sides();

  public List<Vector3> vertexes() {
    return sides().stream()
        .flatMap(x -> x.vertices.stream())
        .collect(toList());
  }

  public Solid apply(Matrix4 matrix) {
    return new TransformedSolid(this, matrix);
  }

  public Solid add(Solid solid) {
    List<Polygon> thisSides = sides();
    if (thisSides.size() == 0) {
      return solid;
    }
    List<Polygon> thatSides = solid.sides();
    if (thatSides.size() == 0) {
      return this;
    }
    return new SolidImpl(CSG.union(thisSides, thatSides));
  }

  public Solid add(Solid solid, Alignment<?> alignment) {
    return add(alignment.align(this, solid));
  }

  public Solid sub(Solid solid) {
    List<Polygon> thisSides = sides();
    if (thisSides.size() == 0) {
      return this;
    }
    List<Polygon> thatSides = solid.sides();
    if (thatSides.size() == 0) {
      return this;
    }
    return new SolidImpl(CSG.difference(thisSides, thatSides));
  }

  public Solid sub(Solid solid, Alignment<?> alignment) {
    return sub(alignment.align(this, solid));
  }

  public Solid intersect(Solid solid) {
    List<Polygon> thisSides = sides();
    if (thisSides.size() == 0) {
      return this;
    }
    List<Polygon> thatSides = solid.sides();
    if (thatSides.size() == 0) {
      return solid;
    }
    return new SolidImpl(CSG.intersect(thisSides, thatSides));
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
    return JSolid.convexHull(vertexes());
  }
}
