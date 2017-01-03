package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.vx;
import static com.mikosik.jsolid.JSolid.vy;
import static com.mikosik.jsolid.JSolid.vz;
import static com.mikosik.jsolid.JSolid.x;
import static com.mikosik.jsolid.JSolid.y;
import static com.mikosik.jsolid.JSolid.z;
import static java.util.stream.Collectors.toList;

import java.util.List;

import eu.mihosoft.vrl.v3d.CSG;

public abstract class AbstractSolid implements Solid {

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

  @Deprecated
  public Solid rotate(Vector3 direction, double angle) {
    return apply(rotationMatrix(direction, angle));
  }

  public Solid rotate(Axis<?> direction, double angle) {
    return apply(direction.rotateMatrix(angle));
  }

  private static Matrix4 rotationMatrix(Vector3 axis, double angle) {
    if (axis.equals(vx(1))) {
      return x().rotateMatrix(angle);
    } else if (axis.equals(vy(1))) {
      return y().rotateMatrix(angle);
    } else if (axis.equals(vz(1))) {
      return z().rotateMatrix(angle);
    } else {
      throw new IllegalArgumentException("Axis must be one of vx(1), vy(1), vz(1).");
    }
  }

  public Solid mirror(Axis<?> direction) {
    return apply(direction.mirrorMatrix());
  }

  @Deprecated
  public Solid scale(Vector3 factor) {
    return scale(factor.x, factor.y, factor.z);
  }

  private Solid scale(double x, double y, double z) {
    Matrix4 xm = x().scaleMatrix(x);
    Matrix4 ym = y().scaleMatrix(y);
    Matrix4 zm = z().scaleMatrix(z);
    return apply(xm.mul(ym.mul(zm)));
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
