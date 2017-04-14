package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.JSolid.range;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.mikosik.jsolid.d3.Anchor3;
import com.mikosik.jsolid.d3.Axis;
import com.mikosik.jsolid.util.Check;

public abstract class Anchor1 {
  public final double edge;

  private Anchor1(double edge) {
    this.edge = edge;
  }

  public static Anchor1 min() {
    return new Anchor1(-1.0) {
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

      public Anchor1 opposite() {
        return max();
      }
    };
  }

  private static <T> Stream<T> stream(Iterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }

  public static Anchor1 max() {
    return new Anchor1(1.0) {
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

      public Anchor1 opposite() {
        return min();
      }
    };
  }

  public static Anchor1 center() {
    return new Anchor1(0.0) {
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

      public Anchor1 opposite() {
        return this;
      }
    };
  }

  public abstract double of(Iterable<Double> values);

  public abstract Anchor1 opposite();

  public <A extends Axis<A>> Anchor3<A> on(Axis<A> axis) {
    return new Anchor3<A>(axis, this);
  }

  public abstract Range moveTo(Range range, double value);

  public abstract Range resizeTo(Range range, double size);

  public Range resizeBy(Range range, double delta) {
    return resizeTo(range, range.size() + delta);
  }
}
