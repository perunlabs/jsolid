package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.v0;
import static java.lang.Math.sqrt;

import com.mikosik.jsolid.util.Check;
import com.mikosik.jsolid.util.Hash;

public class Vector3 {
  public final double x;
  public final double y;
  public final double z;

  public static Vector3 vector3(double x, double y, double z) {
    return new Vector3(
        Check.isFinite(x),
        Check.isFinite(y),
        Check.isFinite(z));
  }

  Vector3(double x, double y, double z) {
    this.x = Check.isFinite(x);
    this.y = Check.isFinite(y);
    this.z = Check.isFinite(z);
  }

  public double length() {
    return sqrt(x * x + y * y + z * z);
  }

  public Vector3 add(Vector3 v) {
    return new Vector3(
        x + v.x,
        y + v.y,
        z + v.z);
  }

  public Vector3 sub(Vector3 v) {
    return new Vector3(
        x - v.x,
        y - v.y,
        z - v.z);
  }

  public Vector3 dif(Vector3 v) {
    return new Vector3(
        Math.abs(x - v.x),
        Math.abs(y - v.y),
        Math.abs(z - v.z));
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

  public Vector3 abs() {
    return new Vector3(Math.abs(x), Math.abs(y), Math.abs(z));
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
    if (length == 0) {
      return v0();
    } else {
      return new Vector3(
          x / length,
          y / length,
          z / length);
    }
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
    return Hash.hash(x, y, z);
  }

  public String toString() {
    return "v(" + x + ", " + y + ", " + z + ")";
  }
}
