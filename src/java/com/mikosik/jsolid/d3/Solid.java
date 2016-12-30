package com.mikosik.jsolid.d3;

import java.util.List;

import eu.mihosoft.vrl.v3d.CSG;

public interface Solid {
  public List<Vector3> vertexes();

  public Solid add(Solid solid);

  public Solid add(Solid solid, Alignment<?> alignment);

  public Solid sub(Solid solid);

  public Solid sub(Solid solid, Alignment<?> alignment);

  @Deprecated
  public Solid plus(Solid solid);

  @Deprecated
  public Solid plus(Solid solid, Alignment<?> alignment);

  @Deprecated
  public Solid minus(Solid solid);

  @Deprecated
  public Solid minus(Solid solid, Alignment<?> alignment);

  public Solid intersect(Solid solid);

  public Solid move(Vector3 position);

  public Solid move(Anchor<?> anchor, double value);

  public Solid rotate(Vector3 direction, double angle);

  public Solid mirror(Vector3 planNormal);

  public Solid scale(Vector3 factor);

  public Solid convexHull();

  public String toStl();

  public CSG toCsg();
}
