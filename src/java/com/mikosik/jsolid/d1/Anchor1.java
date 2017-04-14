package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.JSolid.range;

import com.mikosik.jsolid.util.Check;

public abstract class Anchor1 {
  public static Anchor1 min() {
    return new Anchor1() {
      public Range moveTo(Range range, double value) {
        return range(value, value + range.size());
      }

      public Range resizeTo(Range range, double size) {
        Check.notNegative(size);
        return range(range.min, range.min + size);
      }
    };
  }

  public static Anchor1 max() {
    return new Anchor1() {
      public Range moveTo(Range range, double value) {
        return range(value - range.size(), value);
      }

      public Range resizeTo(Range range, double size) {
        Check.notNegative(size);
        return range(range.max - size, range.max);
      }
    };
  }

  public static Anchor1 center() {
    return new Anchor1() {
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
  }

  public abstract Range moveTo(Range range, double value);

  public abstract Range resizeTo(Range range, double size);

  public Range resizeBy(Range range, double delta) {
    return resizeTo(range, range.size() + delta);
  }
}
