package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.JSolid.range;

import com.mikosik.jsolid.util.Check;

public enum Anchor1 {
  MIN() {
    public Range moveTo(Range range, double value) {
      return range(value, value + range.size());
    }

    public Range resizeTo(Range range, double size) {
      Check.notNegative(size);
      return range(range.min, range.min + size);
    }
  },
  MAX() {
    public Range moveTo(Range range, double value) {
      return range(value - range.size(), value);
    }

    public Range resizeTo(Range range, double size) {
      Check.notNegative(size);
      return range(range.max - size, range.max);
    }
  },
  CENTER() {
    public Range moveTo(Range range, double value) {
      double halfSize = range.size() / 2;
      return range(value - halfSize, value + halfSize);
    }

    public Range resizeTo(Range range, double size) {
      Check.notNegative(size);
      double center = range.center();
      double halfSize = size / 2;
      return range(center - halfSize, center + halfSize);
    }
  };

  public abstract Range moveTo(Range range, double value);

  public abstract Range resizeTo(Range range, double size);

  public Range resizeBy(Range range, double delta) {
    return resizeTo(range, range.size() + delta);
  }
}
