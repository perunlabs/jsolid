package com.mikosik.jsolid.d2;

import static com.mikosik.jsolid.JSolid.v;
import static java.lang.Math.acos;

import java.util.ArrayList;
import java.util.List;

public class Circle implements Polygon {
  private static final double PI_X_2 = 2 * Math.PI;
  private static double DELTA = 0.001;
  private static int MIN_VERTEX_COUNT = 8;
  private static double MIN_ANGLE_STEP = 2 * Math.PI / MIN_VERTEX_COUNT;

  private final List<Vector2> vertexes;

  public Circle(double radius) {
    this.vertexes = generateVertexes(radius);
  }

  private static List<Vector2> generateVertexes(double radius) {
    int vertexCount = vertexCount(radius, PI_X_2);
    ArrayList<Vector2> result = new ArrayList<>();
    for (int i = 0; i < vertexCount; i++) {
      double angle = (i * (PI_X_2)) / vertexCount;
      result.add(v(Math.cos(angle) * radius, Math.sin(angle) * radius));
    }
    return result;
  }

  public static int vertexCount(double radius, double circleAngle) {
    double angleStep = minAngleStep(radius, DELTA);
    return (int) Math.ceil(circleAngle / angleStep);
  }

  private static double minAngleStep(double radius, double delta) {
    if (delta < radius) {
      return 2 * acos((radius - delta) / radius);
    } else {
      return MIN_ANGLE_STEP;
    }
  }

  public List<Vector2> vertexes() {
    return vertexes;
  }
}
