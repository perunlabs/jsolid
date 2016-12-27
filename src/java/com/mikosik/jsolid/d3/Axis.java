package com.mikosik.jsolid.d3;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

import java.util.function.BinaryOperator;

import com.mikosik.jsolid.JSolid;
import com.mikosik.jsolid.d3.Anchor.CenterAnchor;
import com.mikosik.jsolid.d3.Anchor.EdgeAnchor;
import com.mikosik.jsolid.d3.Anchor.ZeroAnchor;

public abstract class Axis<A extends Axis<A>> extends Vector3 {
  public static final XAxis X = new XAxis();
  public static final YAxis Y = new YAxis();
  public static final ZAxis Z = new ZAxis();

  private Axis(double x, double y, double z) {
    super(x, y, z);
  }

  public abstract double coordinate(Vector3 v);

  public abstract Vector3 v(double coordinate);

  public Anchor<A> min() {
    return new EdgeAnchor<>(this, minReduce(), MAX_VALUE);
  }

  public Anchor<A> max() {
    return new EdgeAnchor<>(this, maxReduce(), MIN_VALUE);
  }

  public Anchor<A> center() {
    return new CenterAnchor<>(this, min(), max());
  }

  public Anchor<A> zero() {
    return new ZeroAnchor<>(this);
  }

  public double size(Solid solid) {
    return max().valueIn(solid) - min().valueIn(solid);
  }

  public static class XAxis extends Axis<XAxis> {
    public XAxis() {
      super(1, 0, 0);
    }

    public double coordinate(Vector3 v) {
      return v.x;
    }

    public Vector3 v(double coordinate) {
      return JSolid.v(coordinate, 0, 0);
    }
  }

  public static class YAxis extends Axis<YAxis> {
    public YAxis() {
      super(0, 1, 0);
    }

    public double coordinate(Vector3 v) {
      return v.y;
    }

    public Vector3 v(double coordinate) {
      return JSolid.v(0, coordinate, 0);
    }
  }

  public static class ZAxis extends Axis<ZAxis> {
    public ZAxis() {
      super(0, 0, 1);
    }

    public double coordinate(Vector3 v) {
      return v.z;
    }

    public Vector3 v(double coordinate) {
      return JSolid.v(0, 0, coordinate);
    }
  }

  private static BinaryOperator<Double> minReduce() {
    return (a, b) -> a < b ? a : b;
  }

  private static BinaryOperator<Double> maxReduce() {
    return (a, b) -> a < b ? b : a;
  }
}
