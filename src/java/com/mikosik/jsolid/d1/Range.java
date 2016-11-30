package com.mikosik.jsolid.d1;

public final class Range {
  public final double low;
  public final double high;

  public Range(double length) {
    this.low = -length / 2;
    this.high = length / 2;
  }

  public Range(double v1, double v2) {
    this.low = v1 < v2 ? v1 : v2;
    this.high = v1 < v2 ? v2 : v1;
  }

  public double length() {
    return high - low;
  }
}
