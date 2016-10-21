package com.mikosik.jsolid.d2;

import static com.mikosik.jsolid.Solids.range;
import static com.mikosik.jsolid.Solids.v;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.min;
import static java.lang.Math.sin;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.mikosik.jsolid.d1.Range;

public class Rectangle implements Polygon {
  private final Range xd;
  private final Range yd;
  private final double cornerR;

  public Rectangle() {
    this.xd = range();
    this.yd = range();
    this.cornerR = 0;
  }

  public Rectangle(Range xd, Range yd, double cornerR) {
    this.xd = xd;
    this.yd = yd;
    this.cornerR = cornerR;
  }

  public Rectangle xd(double xd) {
    return new Rectangle(this.xd.vd(xd), yd, cornerR);
  }

  public Rectangle xd(Range xd) {
    return new Rectangle(xd, yd, cornerR);
  }

  public Rectangle yd(double yd) {
    return new Rectangle(xd, this.yd.vd(yd), cornerR);
  }

  public Rectangle yd(Range yd) {
    return new Rectangle(xd, yd, cornerR);
  }

  public Rectangle x(double x1, double x2) {
    return new Rectangle(xd.v(x1, x2), yd, cornerR);
  }

  public Rectangle y(double y1, double y2) {
    return new Rectangle(xd, yd.v(y1, y2), cornerR);
  }

  public Rectangle x1(double x1) {
    return new Rectangle(xd.v1(x1), yd, cornerR);
  }

  public Rectangle x2(double x2) {
    return new Rectangle(xd.v2(x2), yd, cornerR);
  }

  public Rectangle y1(double y1) {
    return new Rectangle(xd, yd.v1(y1), cornerR);
  }

  public Rectangle y2(double y2) {
    return new Rectangle(xd, yd.v2(y2), cornerR);
  }

  public Rectangle cornerR(double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("cornerR should be greater than 0, but is " + radius);
    }
    return new Rectangle(xd, yd, radius);
  }

  public Rectangle cornerRMax() {
    return new Rectangle(xd, yd, Double.MAX_VALUE);
  }

  public List<Vector2> vertexes() {
    double x1 = xd.low();
    double x2 = xd.high();
    double y1 = yd.low();
    double y2 = yd.high();
    if (0 < cornerR) {
      return roundedVertexes(x1, y1, x2, y2);
    } else {
      return asList(v(x1, y1), v(x2, y1), v(x2, y2), v(x1, y2));
    }
  }

  private List<Vector2> roundedVertexes(double x1, double y1, double x2, double y2) {
    double xLength = this.xd.length();
    double yLength = this.yd.length();
    double maxRadius = min(xLength, yLength) / 2;
    double radius = min(maxRadius, cornerR);
    double count = Circle.vertexCount(radius, 0.5 * Math.PI);
    ArrayList<Vector2> result = new ArrayList<>();
    addCornerVertexes(result, v(x1 + radius, y1 + radius), radius, PI, count);
    if (2 * radius < xLength) {
      result.add(v(x1 + radius, y1));
    }

    addCornerVertexes(result, v(x2 - radius, y1 + radius), radius, 1.5 * PI, count);
    if (2 * radius < yLength) {
      result.add(v(x2, y1 + radius));
    }

    addCornerVertexes(result, v(x2 - radius, y2 - radius), radius, 0, count);
    if (2 * radius < xLength) {
      result.add(v(x2 - radius, y2));
    }

    addCornerVertexes(result, v(x1 + radius, y2 - radius), radius, 0.5 * PI, count);
    if (2 * radius < yLength) {
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
      result.add(v0.plus(v));
    }
  }
}
