package com.mikosik.jsolid.d3;

import java.util.List;

import com.mikosik.jsolid.d3.Anchor3.EdgeAnchor;

import eu.mihosoft.vrl.v3d.Polygon;

public interface Solid {
  public List<Polygon> sides();

  public List<Vector3> vertexes();

  public Solid apply(Matrix4 matrix);

  public Solid add(Solid solid);

  public Solid add(Solid solid, EdgeAnchor<?>... edges);

  public Solid add(Solid solid, Alignment... alignments);

  public Solid sub(Solid solid);

  public Solid sub(Solid solid, EdgeAnchor<?>... edges);

  public Solid sub(Solid solid, Alignment... alignments);

  public Solid intersect(Solid solid);

  public Solid intersect(Solid solid, EdgeAnchor<?>... edges);

  public Solid intersect(Solid solid, Alignment... alignments);

  public Solid moveBy(Vector3 shift);

  public Solid moveTo(Anchor3<?> anchor, double position);

  public Solid rotate(Axis<?> direction, double angle);

  public Solid mirror(Axis<?> planNormal);

  public Solid scale(Axis<?> direction, double factor);

  public Solid convexHull();

  public Solid mirroredTwins(Vector3 shift);
}
