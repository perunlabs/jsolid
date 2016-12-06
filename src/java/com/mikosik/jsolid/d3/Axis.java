package com.mikosik.jsolid.d3;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

import java.util.function.BinaryOperator;

import com.mikosik.jsolid.JSolid;
import com.mikosik.jsolid.d3.Anchor.CenterAnchor;
import com.mikosik.jsolid.d3.Anchor.EdgeAnchor;
import com.mikosik.jsolid.d3.Anchor.ZeroAnchor;

import eu.mihosoft.vrl.v3d.Vector3d;

public abstract class Axis<A extends Axis<A>> extends Vector3d {
  public static final XAxis X = new XAxis();
  public static final YAxis Y = new YAxis();
  public static final ZAxis Z = new ZAxis();

  private Axis(double x, double y, double z) {
    super(x, y, z);
  }

  public abstract double coordinate(Vector3d v);

  public abstract Vector3d v(double coordinate);

  public Anchor<A> minAnchor() {
    return new EdgeAnchor<>(this, minReduce(), MAX_VALUE);
  }

  public Anchor<A> maxAnchor() {
    return new EdgeAnchor<>(this, maxReduce(), MIN_VALUE);
  }

  public Anchor<A> centerAnchor() {
    return new CenterAnchor<>(this, minAnchor(), maxAnchor());
  }

  public Anchor<A> zeroAnchor() {
    return new ZeroAnchor<>(this);
  }

  public static class XAxis extends Axis<XAxis> {
    public XAxis() {
      super(1, 0, 0);
    }

    public double coordinate(Vector3d v) {
      return v.x;
    }

    public Vector3d v(double coordinate) {
      return JSolid.v(coordinate, 0, 0);
    }
  }

  public static class YAxis extends Axis<YAxis> {
    public YAxis() {
      super(0, 1, 0);
    }

    public double coordinate(Vector3d v) {
      return v.y;
    }

    public Vector3d v(double coordinate) {
      return JSolid.v(0, coordinate, 0);
    }
  }

  public static class ZAxis extends Axis<ZAxis> {
    public ZAxis() {
      super(0, 0, 1);
    }

    public double coordinate(Vector3d v) {
      return v.z;
    }

    public Vector3d v(double coordinate) {
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
