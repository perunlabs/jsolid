package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.JSolid.range;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

      public double of(Iterable<Double> values) {
        return stream(values)
            .reduce((a, b) -> a < b ? a : b)
            .orElseThrow(() -> new IllegalArgumentException());
      }
    };
  }

  private static <T> Stream<T> stream(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
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

      public double of(Iterable<Double> values) {
        return stream(values)
            .reduce((a, b) -> a < b ? b : a)
            .orElseThrow(() -> new IllegalArgumentException());
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

      public double of(Iterable<Double> values) {
        double min = min().of(values);
        double max = max().of(values);
        return (min + max) / 2;
      }
    };
  }

  public abstract double of(Iterable<Double> values);

  public abstract Range moveTo(Range range, double value);

  public abstract Range resizeTo(Range range, double size);

  public Range resizeBy(Range range, double delta) {
    return resizeTo(range, range.size() + delta);
  }
}
