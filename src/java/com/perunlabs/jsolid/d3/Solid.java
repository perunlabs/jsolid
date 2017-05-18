package com.perunlabs.jsolid.d3;

import java.util.List;

import com.perunlabs.jsolid.d1.Angle;

import eu.mihosoft.vrl.v3d.Polygon;

public interface Solid {
  public List<Polygon> sides();

  public List<Vector3> vertexes();

  public Solid apply(Matrix4 matrix);

  public Solid add(Solid solid);

  public Solid add(Solid solid, Anchor3<?>... edges);

  public Solid add(Solid solid, Alignment... alignments);

  public Solid sub(Solid solid);

  public Solid sub(Solid solid, Anchor3<?>... edges);

  public Solid sub(Solid solid, Alignment... alignments);

  public Solid intersect(Solid solid);

  public Solid intersect(Solid solid, Anchor3<?>... edges);

  public Solid intersect(Solid solid, Alignment... alignments);

  public Solid moveBy(Vector3 shift);

  public Solid moveTo(Anchor3<?> anchor, double position);

  @Deprecated
  public Solid rotate(Axis<?> axis, double angle);

  public Solid rotate(Axis<?> axis, Angle angle);

  public Solid mirror(Axis<?> planNormal);

  public Solid scale(Axis<?> direction, double factor);

  public Solid convexHull();

  public Solid mirroredTwins(Vector3 shift);
}
