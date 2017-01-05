package com.mikosik.jsolid.d3;

import static java.util.stream.Collectors.toList;

import java.util.List;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Polygon;

public abstract class AbstractSolid implements Solid {
  public List<Polygon> sides() {
    return toCsg().getPolygons();
  }

  public List<Vector3> vertexes() {
    return toCsg().getPolygons().stream()
        .flatMap(x -> x.vertices.stream())
        .collect(toList());
  }

  public Solid apply(Matrix4 matrix) {
    return new TransformedSolid(this, matrix);
  }

  public Solid add(Solid solid) {
    CSG thisCsg = toCsg();
    if (thisCsg.getPolygons().size() == 0) {
      return solid;
    }
    CSG thatCsg = solid.toCsg();
    if (thatCsg.getPolygons().size() == 0) {
      return this;
    }

    return new CsgSolid(toCsg().union(solid.toCsg()));
  }

  public Solid add(Solid solid, Alignment<?> alignment) {
    return add(alignment.align(this, solid));
  }

  public Solid sub(Solid solid) {
    CSG thisCsg = toCsg();
    if (thisCsg.getPolygons().size() == 0) {
      return this;
    }
    CSG thatCsg = solid.toCsg();
    if (thatCsg.getPolygons().size() == 0) {
      return this;
    }
    return new CsgSolid(thisCsg.difference(thatCsg));
  }

  public Solid sub(Solid solid, Alignment<?> alignment) {
    return sub(alignment.align(this, solid));
  }

  public Solid intersect(Solid solid) {
    CSG thisCsg = toCsg();
    if (thisCsg.getPolygons().size() == 0) {
      return this;
    }
    CSG thatCsg = solid.toCsg();
    if (thatCsg.getPolygons().size() == 0) {
      return solid;
    }
    return new CsgSolid(toCsg().intersect(solid.toCsg()));
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
    return new CsgSolid(toCsg().hull());
  }

  public String toStl() {
    return toCsg().toStlString();
  }
}
