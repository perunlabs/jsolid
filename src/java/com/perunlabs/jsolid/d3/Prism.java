package com.perunlabs.jsolid.d3;

import static com.perunlabs.jsolid.JSolid.v;
import static com.perunlabs.jsolid.util.Lists.reverse;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.perunlabs.jsolid.d1.Range;
import com.perunlabs.jsolid.d2.Geometry;
import com.perunlabs.jsolid.d2.Polygon;
import com.perunlabs.jsolid.d2.Vector2;
import com.perunlabs.jsolid.d3.op.AbstractSolid;

public final class Prism extends AbstractSolid {
  private final Polygon base;
  private final Range zRange;

  public Prism(Polygon base, Range zRange) {
    this.base = requireNonNull(base);
    this.zRange = requireNonNull(zRange);
  }

  protected List<eu.mihosoft.vrl.v3d.Polygon> calculateSides() {
    if (!Geometry.isConvexCounterClockwisePolygon(base.vertexes())) {
      throw new IllegalStateException("base is not convex, counter clockwise polygon.");
    }
    double bottom = zRange.min;
    double top = zRange.max;
    List<eu.mihosoft.vrl.v3d.Polygon> polygons = new ArrayList<>();
    polygons.add(base(reverse(base.vertexes()), bottom));
    polygons.add(base(base.vertexes(), top));
    List<Vector2> vertexes = base.vertexes();
    for (int i = 0; i < vertexes.size(); i++) {
      int next = (i + 1) % vertexes.size();
      polygons.add(poly(
          v(vertexes.get(i), bottom),
          v(vertexes.get(next), bottom),
          v(vertexes.get(next), top),
          v(vertexes.get(i), top)));
    }
    return polygons;
  }

  private eu.mihosoft.vrl.v3d.Polygon base(List<Vector2> vertexes, double bottom) {
    return new eu.mihosoft.vrl.v3d.Polygon(
        vertexes
            .stream()
            .map(v2 -> v(v2.x, v2.y, bottom))
            .collect(toList()));
  }

  private static eu.mihosoft.vrl.v3d.Polygon poly(Vector3... points) {
    return eu.mihosoft.vrl.v3d.Polygon.fromPoints(points);
  }
}
