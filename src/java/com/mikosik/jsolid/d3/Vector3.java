package com.mikosik.jsolid.d3;

import static java.lang.Math.sqrt;

import java.util.Objects;

import com.mikosik.jsolid.util.Check;

public class Vector3 {
  public final double x;
  public final double y;
  public final double z;

  public static Vector3 vector3(double x, double y, double z) {
    return new Vector3(x, y, z);
  }

  Vector3(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double length() {
    return sqrt(x * x + y * y + z * z);
  }

  @Deprecated
  public Vector3 plus(Vector3 v) {
    return add(v);
  }

  public Vector3 add(Vector3 v) {
    return new Vector3(
        x + v.x,
        y + v.y,
        z + v.z);
  }

  @Deprecated
  public Vector3 minus(Vector3 v) {
    return sub(v);
  }

  public Vector3 sub(Vector3 v) {
    return new Vector3(
        x - v.x,
        y - v.y,
        z - v.z);
  }

  @Deprecated
  public Vector3 negate() {
    return neg();
  }

  public Vector3 neg() {
    return new Vector3(-x, -y, -z);
  }

  public Vector3 mul(double multiplier) {
    return new Vector3(x * multiplier, y * multiplier, z * multiplier);
  }

  public Vector3 div(double divisor) {
    return new Vector3(x / divisor, y / divisor, z / divisor);
  }

  public double dot(Vector3 v) {
    return x * v.x + y * v.y + z * v.z;
  }

  public Vector3 cross(Vector3 v) {
    return new Vector3(
        y * v.z - z * v.y,
        z * v.x - x * v.z,
        x * v.y - y * v.x);
  }

  public Vector3 normalize() {
    double length = length();
    return new Vector3(
        x / length,
        y / length,
        z / length);
  }

  public Vector3 interpolate(Vector3 v, double value) {
    Check.notNegative(value);
    if (1.0 < value) {
      throw new IllegalArgumentException();
    }
    return new Vector3(
        x + value * (v.x - x),
        y + value * (v.y - y),
        z + value * (v.z - z));
  }

  public boolean equals(Object object) {
    return object instanceof Vector3 && equals((Vector3) object);
  }

  private boolean equals(Vector3 that) {
    return this.x == that.x
        && this.y == that.y
        && this.z == that.z;
  }

  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  public String toString() {
    return "v(" + x + ", " + y + ", " + z + ")";
  }
}
