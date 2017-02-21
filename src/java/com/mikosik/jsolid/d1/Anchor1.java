package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.JSolid.range;

public enum Anchor1 {
  MIN() {
    public Range moveTo(Range range, double value) {
      return range(value, value + range.size());
    }
  },
  MAX() {
    public Range moveTo(Range range, double value) {
      return range(value - range.size(), value);
    }
  },
  CENTER() {
    public Range moveTo(Range range, double value) {
      double halfSize = range.size() / 2;
      return range(value - halfSize, value + halfSize);
    }
  };
  public abstract Range moveTo(Range range, double value);
}
