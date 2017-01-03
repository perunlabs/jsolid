package com.mikosik.jsolid.d3;

import java.util.List;

import eu.mihosoft.vrl.v3d.CSG;

public interface Solid {
  public List<Vector3> vertexes();

  public Solid apply(Matrix4 matrix);

  public Solid add(Solid solid);

  public Solid add(Solid solid, Alignment<?> alignment);

  public Solid sub(Solid solid);

  public Solid sub(Solid solid, Alignment<?> alignment);

  public Solid intersect(Solid solid);

  public Solid move(Vector3 position);

  public Solid move(Anchor<?> anchor, double value);

  public Solid rotate(Axis<?> direction, double angle);

  public Solid mirror(Axis<?> planNormal);

  public Solid scale(Axis<?> direction, double factor);

  public Solid convexHull();

  public String toStl();

  public CSG toCsg();
}
