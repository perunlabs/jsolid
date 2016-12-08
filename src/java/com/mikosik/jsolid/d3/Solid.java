package com.mikosik.jsolid.d3;

import java.util.List;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Vector3d;

public interface Solid {
  public List<Vector3d> vertexes();

  public Solid plus(Solid solid);

  public Solid plus(Solid solid, Alignment<?> alignment);

  public Solid minus(Solid solid);

  public Solid minus(Solid solid, Alignment<?> alignment);

  public Solid intersect(Solid solid);

  public Solid move(Vector3d position);

  public Solid move(Anchor<?> anchor, double value);

  public Solid rotate(Vector3d direction, double angle);

  public Solid mirror(Vector3d planNormal);

  public Solid scale(Vector3d factor);

  public Solid convexHull();

  public String toStl();

  public CSG toCsg();
}
