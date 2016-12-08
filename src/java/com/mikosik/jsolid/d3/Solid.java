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

  public Solid move(Vector3d vector);

  public Solid move(Anchor<?> anchor, double value);

  @Deprecated
  public Solid moveAabbEdgeTo(Vector3d direction, double position);

  public Solid rotate(Vector3d direction, double angle);

  public Solid mirror(Vector3d planNormal);

  public Solid scale(Vector3d factor);

  @Deprecated
  public double aabbEdge(Vector3d direction);

  public Solid convexHull();

  public String toStl();

  public CSG toCsg();
}
