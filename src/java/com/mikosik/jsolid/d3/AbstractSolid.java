package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.vx;
import static com.mikosik.jsolid.JSolid.vy;
import static com.mikosik.jsolid.JSolid.vz;
import static com.mikosik.jsolid.d3.VectorUtils.abs;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

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
    return new CsgSolid(toCsg().union(solid.toCsg()));
  }

  public Solid plusTouching(Vector3d direction, Solid solid) {
    double touchPoint = aabbEdge(direction);
    Solid moved = solid.moveAabbEdgeTo(direction.negated(), touchPoint);
    return plus(moved);
  }

  public Solid minus(Solid solid) {
    return new CsgSolid(toCsg().difference(solid.toCsg()));
  }

  public Solid minusTouching(Vector3d direction, Solid solid) {
    double touchPoint = aabbEdge(direction);
    Solid moved = solid.moveAabbEdgeTo(direction, touchPoint);
    return minus(moved);
  }

  public Solid intersect(Solid solid) {
    return new CsgSolid(toCsg().intersect(solid.toCsg()));
  }

  public Solid move(Vector3d vector) {
    return new CsgSolid(toCsg().transformed(Transform.unity().translate(vector)));
  }

  public Solid moveAabbEdgeTo(Vector3d direction, double position) {
    return move(abs(direction).times(position - aabbEdge(direction)));
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

  public double aabbEdge(Vector3d direction) {
    if (direction.equals(vx(-1))) {
      return min(v -> v.x);
    } else if (direction.equals(vx(1))) {
      return max(v -> v.x);
    } else if (direction.equals(vy(-1))) {
      return min(v -> v.y);
    } else if (direction.equals(vy(1))) {
      return max(v -> v.y);
    } else if (direction.equals(vz(-1))) {
      return min(v -> v.z);
    } else if (direction.equals(vz(1))) {
      return max(v -> v.z);
    } else {
      throw new IllegalArgumentException(
          "axle must be one of vx(-1), vx(1), vy(-1), vy(1), vz(-1), vz(1).");
    }
  }

  private double max(Function<? super Vector3d, ? extends Double> componentGetter) {
    return maxValue(componentGetter, Double::compareTo);
  }

  private double min(Function<? super Vector3d, ? extends Double> componentGetter) {
    return maxValue(componentGetter, (a, b) -> b.compareTo(a));
  }

  private double maxValue(Function<? super Vector3d, ? extends Double> componentGetter,
      Comparator<Double> comparator) {
    return vertexes().stream()
        .map(componentGetter)
        .max(comparator)
        .orElseThrow(() -> new RuntimeException("Solid has no vertexes."));
  }

  public Solid convexHull() {
    return new CsgSolid(toCsg().hull());
  }

  public String toStl() {
    return toCsg().toStlString();
  }
}
