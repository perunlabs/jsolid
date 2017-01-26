package com.mikosik.jsolid.d3;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public abstract class Anchor<A extends Axis<A>> {
  public final Axis<A> axis;

  private Anchor(Axis<A> axis) {
    this.axis = axis;
  }

  public abstract double valueIn(Solid solid);

  public Vector3 vectorIn(Solid solid) {
    return axis.v(valueIn(solid));
  }

  public static class EdgeAnchor<A extends Axis<A>> extends Anchor<A> {
    private final BinaryOperator<Double> reduceOperation;
    private final double reduceIdentity;
    private final Supplier<EdgeAnchor<A>> other;

    public EdgeAnchor(Axis<A> axis, BinaryOperator<Double> reduceOperation,
        double reduceIdentity, Supplier<EdgeAnchor<A>> other) {
      super(axis);
      this.reduceOperation = reduceOperation;
      this.reduceIdentity = reduceIdentity;
      this.other = other;
    }

    public double valueIn(Solid solid) {
      return solid.vertexes().stream()
          .map(v -> axis.coordinate(v))
          .reduce(reduceIdentity, reduceOperation);
    }

    public EdgeAnchor<A> other() {
      return other.get();
    }
  }

  public static class CenterAnchor<A extends Axis<A>> extends Anchor<A> {
    private final Anchor<A> minAnchor;
    private final Anchor<A> maxAnchor;

    public CenterAnchor(Axis<A> axis, Anchor<A> minAnchor, Anchor<A> maxAnchor) {
      super(axis);
      this.minAnchor = minAnchor;
      this.maxAnchor = maxAnchor;
    }

    public double valueIn(Solid solid) {
      return (minAnchor.valueIn(solid) + maxAnchor.valueIn(solid)) / 2;
    }
  }

  public static class ZeroAnchor<A extends Axis<A>> extends Anchor<A> {
    public ZeroAnchor(Axis<A> axis) {
      super(axis);
    }

    public double valueIn(Solid solid) {
      return 0;
    }
  }
}
