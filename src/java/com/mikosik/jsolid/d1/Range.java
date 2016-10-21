package com.mikosik.jsolid.d1;

import static java.lang.Math.abs;

public final class Range {
  public final double v1;
  public final double v2;

  public Range() {
    this.v1 = 0;
    this.v2 = 0;
  }

  public Range(double v1, double v2) {
    this.v1 = v1;
    this.v2 = v2;
  }

  public Range v1(double v1) {
    return new Range(v1, v2);
  }

  public Range v2(double v2) {
    return new Range(v1, v2);
  }

  public Range v(double v1, double v2) {
    return new Range(v1, v2);
  }

  public Range vd(double vd) {
    double half = vd / 2;
    return new Range(-half, half);
  }

  public double low() {
    return v1 < v2
        ? v1
        : v2;
  }

  public double high() {
    return v1 < v2
        ? v2
        : v1;
  }

  public double length() {
    return abs(v1 - v2);
  }
}
