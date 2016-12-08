package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.vx;
import static com.mikosik.jsolid.JSolid.vy;
import static com.mikosik.jsolid.JSolid.vz;
import static java.util.stream.Collectors.toList;

import java.util.List;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Plane;
import eu.mihosoft.vrl.v3d.Transform;
import eu.mihosoft.vrl.v3d.Vector3d;

public abstract class AbstractSolid implements Solid {

  public List<Vector3d> vertexes() {
    return toCsg().getPolygons().stream()
        .flatMap(x -> x.vertices.stream())
        .map(x -> x.pos)
        .collect(toList());
  }

  public Solid plus(Solid solid) {
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

  public Solid plus(Solid solid, Alignment<?> alignment) {
    return plus(alignment.align(this, solid));
  }

  public Solid minus(Solid solid) {
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

  public Solid minus(Solid solid, Alignment<?> alignment) {
    return minus(alignment.align(this, solid));
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

  public Solid move(Vector3d position) {
    return translate(position);
  }

  public Solid move(Anchor<?> anchor, double value) {
    return move(anchor.axis.v(value).minus(anchor.vectorIn(this)));
  }

  private CsgSolid translate(Vector3d vector) {
    return new CsgSolid(toCsg().transformed(Transform.unity().translate(vector)));
  }

  public Solid rotate(Vector3d direction, double angle) {
    return new CsgSolid(toCsg().transformed(rotationTransform(direction, angle)));
  }

  private Transform rotationTransform(Vector3d axis, double angle) {
    if (axis.equals(vx(1))) {
      return Transform.unity().rotX(angle);
    } else if (axis.equals(vy(1))) {
      return Transform.unity().rotY(angle);
    } else if (axis.equals(vz(1))) {
      return Transform.unity().rotZ(angle);
    } else {
      throw new IllegalArgumentException("Axis must be one of vx(1), vy(1), vz(1).");
    }
  }

  public Solid mirror(Vector3d planeNormal) {
    return new CsgSolid(toCsg().transformed(Transform.unity().mirror(plane(planeNormal))));
  }

  private Plane plane(Vector3d planeNormal) {
    if (planeNormal.equals(vx(1))) {
      return Plane.YZ_PLANE;
    } else if (planeNormal.equals(vy(1))) {
      return Plane.XZ_PLANE;
    } else if (planeNormal.equals(vz(1))) {
      return Plane.XY_PLANE;
    } else {
      throw new IllegalArgumentException("planeNormal must be one of vx(1), vy(1), vz(1).");
    }
  }

  public Solid scale(Vector3d factor) {
    return new CsgSolid(toCsg().transformed(Transform.unity().scale(factor)));
  }

  public Solid convexHull() {
    return new CsgSolid(toCsg().hull());
  }

  public String toStl() {
    return toCsg().toStlString();
  }
}
