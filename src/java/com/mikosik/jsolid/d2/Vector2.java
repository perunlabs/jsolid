package com.mikosik.jsolid.d2;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static java.lang.String.format;

import java.util.Objects;

public final class Vector2 {
  public final double x;
  public final double y;

  public static Vector2 vector2(double x, double y) {
    return new Vector2(x, y);
  }

  private Vector2(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double length() {
    return sqrt(x * x + y * y);
  }

  public double angle() {
    double result = Math.atan2(y, x);
    if (result < 0) {
      return result + PI * 2;
    } else {
      return result;
    }
  }

  @Deprecated
  public Vector2 plus(Vector2 v) {
    return add(v);
  }

  public Vector2 add(Vector2 v) {
    return vector2(x + v.x, y + v.y);
  }

  @Deprecated
  public Vector2 minus(Vector2 v) {
    return sub(v);
  }

  public Vector2 sub(Vector2 v) {
    return vector2(x - v.x, y - v.y);
  }

  @Deprecated
  public Vector2 negate() {
    return neg();
  }

  public Vector2 neg() {
    return vector2(-x, -y);
  }

  public Vector2 mul(double multiplier) {
    return vector2(x * multiplier, y * multiplier);
  }

  public Vector2 div(double divisor) {
    return vector2(x / divisor, y / divisor);
  }

  public double dot(Vector2 v) {
    return x * v.x + y * v.y;
  }

  public double cross(Vector2 v) {
    return x * v.y - y * v.x;
  }

  public Vector2 normalize() {
    return div(length());
  }

  public boolean equals(Object object) {
    return object instanceof Vector2 && equals((Vector2) object);
  }

  private boolean equals(Vector2 that) {
    return this.x == that.x && this.y == that.y;
  }

  public int hashCode() {
    return Objects.hash(x, y);
  }

  public String toString() {
    return format("v(" + x + ", " + y + ")");
  }
}
