package com.mikosik.jsolid.d3;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Supplier;

import com.mikosik.jsolid.d1.Anchor1;

public abstract class Anchor3<A extends Axis<A>> {
  public final Axis<A> axis;

  private Anchor3(Axis<A> axis) {
    this.axis = axis;
  }

  public abstract double valueIn(Solid solid);

  public Vector3 vectorIn(Solid solid) {
    return axis.v(valueIn(solid));
  }

  public static class EdgeAnchor3<A extends Axis<A>> extends Anchor3<A> {
    private final Anchor1 anchor;
    private final Supplier<EdgeAnchor3<A>> other;

    public EdgeAnchor3(Axis<A> axis, Anchor1 anchor, Supplier<EdgeAnchor3<A>> other) {
      super(axis);
      this.anchor = anchor;
      this.other = other;
    }

    public double valueIn(Solid solid) {
      List<Double> elements = solid.vertexes().stream()
          .map(v -> axis.coordinate(v))
          .collect(toList());
      return anchor.of(elements);
    }

    public EdgeAnchor3<A> other() {
      return other.get();
    }

    public double sign() {
      return anchor.of(asList(-1.0, 1.0));
    }
  }

  public static class CenterAnchor<A extends Axis<A>> extends Anchor3<A> {
    private final Anchor3<A> minAnchor;
    private final Anchor3<A> maxAnchor;

    public CenterAnchor(Axis<A> axis, Anchor3<A> minAnchor, Anchor3<A> maxAnchor) {
      super(axis);
      this.minAnchor = minAnchor;
      this.maxAnchor = maxAnchor;
    }

    public double valueIn(Solid solid) {
      return (minAnchor.valueIn(solid) + maxAnchor.valueIn(solid)) / 2;
    }
  }

  public static class ZeroAnchor<A extends Axis<A>> extends Anchor3<A> {
    public ZeroAnchor(Axis<A> axis) {
      super(axis);
    }

    public double valueIn(Solid solid) {
      return 0;
    }
  }
}
