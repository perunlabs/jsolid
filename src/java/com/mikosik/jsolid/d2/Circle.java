package com.mikosik.jsolid.d2;

import static com.mikosik.jsolid.JSolid.config;
import static java.lang.Math.PI;
import static java.lang.Math.acos;

public final class Circle extends RegularPolygon {
  private static final double PI_X_2 = 2 * Math.PI;
  private static final int MIN_VERTEX_COUNT = 8;
  private static final double MIN_ANGLE_STEP = 2 * Math.PI / MIN_VERTEX_COUNT;

  public Circle(double radius) {
    super(radius, vertexCount(radius, PI_X_2));
  }

  public static int vertexCount(double radius) {
    return vertexCount(radius, 2 * PI);
  }

  public static int vertexCount(double radius, double circleAngle) {
    double angleStep = minAngleStep(radius, config().getCircleToPolygonPrecision());
    return (int) Math.ceil(circleAngle / angleStep);
  }

  private static double minAngleStep(double radius, double circleToPolygonPrecision) {
    if (circleToPolygonPrecision < radius) {
      return 2 * acos((radius - circleToPolygonPrecision) / radius);
    } else {
      return MIN_ANGLE_STEP;
    }
  }
}
