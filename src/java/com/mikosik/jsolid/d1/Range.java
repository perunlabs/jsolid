package com.mikosik.jsolid.d1;

import com.mikosik.jsolid.JSolid;
import com.mikosik.jsolid.util.Check;
import com.mikosik.jsolid.util.Hash;

public final class Range {
  public final double min;
  public final double max;

  public Range(double size) {
    Check.positive(size);
    this.min = -size / 2;
    this.max = size / 2;
  }

  public Range(double v1, double v2) {
    this.min = v1 < v2 ? v1 : v2;
    this.max = v1 < v2 ? v2 : v1;
  }

  public double size() {
    return max - min;
  }

  public double center() {
    return (max + min) / 2;
  }

  public Range min(double min) {
    Check.notNegative(max - min);
    return new Range(min, max);
  }

  public Range max(double max) {
    Check.notNegative(max - min);
    return new Range(min, max);
  }

  public Range resizeTo(double size) {
    return resizeTo(size, JSolid.center());
  }

  public Range resizeTo(double size, Anchor1 anchor1) {
    return anchor1.resizeTo(this, size);
  }

  public Range resizeBy(double delta) {
    return resizeBy(delta, JSolid.center());
  }

  public Range resizeBy(double delta, Anchor1 anchor1) {
    return resizeTo(size() + delta, anchor1);
  }

  public Range moveBy(double value) {
    return new Range(min + value, max + value);
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

  public Range neg() {
    return new Range(-min, -max);
  }

  public boolean equals(Object object) {
    return object instanceof Range && equals((Range) object);
  }

  private boolean equals(Range range) {
    return this.min == range.min && this.max == range.max;
  }

  public int hashCode() {
    return Hash.hash(min, max);
  }

  public String toString() {
    return "range(" + min + ", " + max + ")";
  }
}
