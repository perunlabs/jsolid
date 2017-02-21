package com.mikosik.jsolid.d1;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.mikosik.jsolid.util.Check;
import com.mikosik.jsolid.util.Hash;

public final class Range {
  public final double low;
  public final double high;

  public Range(double size) {
    Check.positive(size);
    this.low = -size / 2;
    this.high = size / 2;
  }

  public Range(double v1, double v2) {
    this.low = v1 < v2 ? v1 : v2;
    this.high = v1 < v2 ? v2 : v1;
  }

  public double size() {
    return high - low;
  }

  public double center() {
    return (high + low) / 2;
  }

  public Range low(double low) {
    Check.notNegative(high - low);
    return new Range(low, high);
  }

  public Range high(double high) {
    Check.notNegative(high - low);
    return new Range(low, high);
  }

  public Range resizeTo(double newSize) {
    Check.notNegative(newSize);
    double center = center();
    double half = newSize / 2;
    return new Range(center - half, center + half);
  }

  public Range resizeBy(double delta) {
    return resizeTo(size() + delta);
  }

  public Range moveBy(double value) {
    return new Range(low + value, high + value);
  }

  public Range moveTo(Anchor1 anchor, double value) {
    return anchor.moveTo(this, value);
  }

  public Range mul(double value) {
    Check.notNegative(value);
    double center = center();
    double newHalfSize = (value * size()) / 2;
    return new Range(center - newHalfSize, center + newHalfSize);
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
