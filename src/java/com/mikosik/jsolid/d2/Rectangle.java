package com.mikosik.jsolid.d2;

import static com.mikosik.jsolid.JSolid.v;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.min;
import static java.lang.Math.sin;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.mikosik.jsolid.d1.Range;

public final class Rectangle implements Polygon {
  private final Range xRange;
  private final Range yRange;
  private final double cornerR;

  public Rectangle(Range xRange, Range yRange, double cornerR) {
    this.xRange = xRange;
    this.yRange = yRange;
    this.cornerR = cornerR;
  }

  public Rectangle cornerR(double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("cornerR should be greater than 0, but is " + radius);
    }
    return new Rectangle(xRange, yRange, radius);
  }

  public List<Vector2> vertexes() {
    double x1 = xRange.min;
    double x2 = xRange.max;
    double y1 = yRange.min;
    double y2 = yRange.max;
    if (0 < cornerR) {
      return roundedVertexes(x1, y1, x2, y2);
    } else {
      return asList(v(x1, y1), v(x2, y1), v(x2, y2), v(x1, y2));
    }
  }

  private List<Vector2> roundedVertexes(double x1, double y1, double x2, double y2) {
    double xSize = this.xRange.size();
    double ySize = this.yRange.size();
    double maxRadius = min(xSize, ySize) / 2;
    double radius = min(maxRadius, cornerR);
    double count = Circle.vertexCount(radius, 0.5 * Math.PI);
    ArrayList<Vector2> result = new ArrayList<>();
    addCornerVertexes(result, v(x1 + radius, y1 + radius), radius, PI, count);
    if (2 * radius < xSize) {
      result.add(v(x1 + radius, y1));
    }

    addCornerVertexes(result, v(x2 - radius, y1 + radius), radius, 1.5 * PI, count);
    if (2 * radius < ySize) {
      result.add(v(x2, y1 + radius));
    }

    addCornerVertexes(result, v(x2 - radius, y2 - radius), radius, 0, count);
    if (2 * radius < xSize) {
      result.add(v(x2 - radius, y2));
    }

    addCornerVertexes(result, v(x1 + radius, y2 - radius), radius, 0.5 * PI, count);
    if (2 * radius < ySize) {
      result.add(v(x1, y2 - radius));
    }

    return result;
  }

  private void addCornerVertexes(ArrayList<Vector2> result,
      Vector2 v0, double radius, double angle0, double count) {
    for (int i = 0; i < count; i++) {
      Vector2 v = v(
          cos(angle0 + (i * 0.5 * PI) / count) * radius,
          sin(angle0 + (i * 0.5 * PI) / count) * radius);
      result.add(v0.add(v));
    }
  }
}
