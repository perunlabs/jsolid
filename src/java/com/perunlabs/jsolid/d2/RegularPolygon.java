package com.perunlabs.jsolid.d2;

import static com.perunlabs.jsolid.JSolid.v;

import java.util.ArrayList;
import java.util.List;

import com.perunlabs.jsolid.util.Check;

public class RegularPolygon implements Polygon {
  private final List<Vector2> vertexes;

  public RegularPolygon(double radius, int vertexCount) {
    this.vertexes = generateVertexes(Check.positive(radius), Check.positive(vertexCount));
  }

  private static List<Vector2> generateVertexes(double radius, int vertexCount) {
    ArrayList<Vector2> result = new ArrayList<>();
    for (int i = 0; i < vertexCount; i++) {
      double angle = (i * (2 * Math.PI)) / vertexCount;
      result.add(v(Math.cos(angle) * radius, Math.sin(angle) * radius));
    }
    return result;
  }

  public List<Vector2> vertexes() {
    return vertexes;
  }
}
