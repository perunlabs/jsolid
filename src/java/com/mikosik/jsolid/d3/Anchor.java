package com.mikosik.jsolid.d3;

import java.util.function.BinaryOperator;

public abstract class Anchor<A extends Axis<A>> {
  public final Axis<A> axis;

  private Anchor(Axis<A> axis) {
    this.axis = axis;
  }

  public abstract double positionIn(Solid solid);

  public static class EdgeAnchor<A extends Axis<A>> extends Anchor<A> {
    private final BinaryOperator<Double> reduceOperation;
    private final double reduceIdentity;

    public EdgeAnchor(Axis<A> axis, BinaryOperator<Double> reduceOperation, double reduceIdentity) {
      super(axis);
      this.reduceOperation = reduceOperation;
      this.reduceIdentity = reduceIdentity;
    }

    public double positionIn(Solid solid) {
      return solid.vertexes().stream()
          .map(v -> axis.coordinate(v))
          .reduce(reduceIdentity, reduceOperation);
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

    public double positionIn(Solid solid) {
      return (minAnchor.positionIn(solid) + maxAnchor.positionIn(solid)) / 2;
    }
  }

  public static class ZeroAnchor<A extends Axis<A>> extends Anchor<A> {
    public ZeroAnchor(Axis<A> axis) {
      super(axis);
    }

    public double positionIn(Solid solid) {
      return 0;
    }
  }
}
