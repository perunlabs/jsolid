package com.mikosik.jsolid.d1;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.mikosik.jsolid.util.Check;
import com.mikosik.jsolid.util.Hash;

public final class Range {
  public final double low;
  public final double high;

  public Range(double length) {
    Check.positive(length);
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

  public double center() {
    return (high + low) / 2;
  }

  public Range resizeTo(double newSize) {
    Check.notNegative(newSize);
    double center = center();
    double half = newSize / 2;
    return new Range(center - half, center + half);
  }

  public Range add(double value) {
    return new Range(low + value, high + value);
  }

  public Range mul(double value) {
    Check.notNegative(value);
    double center = center();
    double newHalfLength = (value * length()) / 2;
    return new Range(center - newHalfLength, center + newHalfLength);
  }

  public Range grow(double value) {
    double center = center();
    return new Range(min(low - value, center), max(high + value, center));
  }

  public boolean equals(Object object) {
    return object instanceof Range && equals((Range) object);
  }

  private boolean equals(Range range) {
    return this.low == range.low && this.high == range.high;
  }

  public int hashCode() {
    return Hash.hash(low, high);
  }

  public String toString() {
    return "range(" + low + ", " + high + ")";
  }
}
