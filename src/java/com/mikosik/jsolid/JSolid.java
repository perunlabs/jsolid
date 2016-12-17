package com.mikosik.jsolid;

import static com.mikosik.jsolid.d2.Vector2.vector2;
import static eu.mihosoft.vrl.v3d.ext.quickhull3d.HullUtil.hull;
import static java.util.Arrays.asList;

import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Circle;
import com.mikosik.jsolid.d2.ConvexPolygon;
import com.mikosik.jsolid.d2.Polygon;
import com.mikosik.jsolid.d2.Rectangle;
import com.mikosik.jsolid.d2.RegularPolygon;
import com.mikosik.jsolid.d2.Vector2;
import com.mikosik.jsolid.d3.Alignment;
import com.mikosik.jsolid.d3.Anchor;
import com.mikosik.jsolid.d3.Axis;
import com.mikosik.jsolid.d3.Axis.XAxis;
import com.mikosik.jsolid.d3.Axis.YAxis;
import com.mikosik.jsolid.d3.Axis.ZAxis;
import com.mikosik.jsolid.d3.Box;
import com.mikosik.jsolid.d3.CsgSolid;
import com.mikosik.jsolid.d3.Pipe;
import com.mikosik.jsolid.d3.Prism;
import com.mikosik.jsolid.d3.Rod;
import com.mikosik.jsolid.d3.Solid;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Vector3d;

public class JSolid {
  public static Range range(double length) {
    return new Range(length);
  }

  public static Range range(double v1, double v2) {
    return new Range(v1, v2);
  }

  public static Vector2 v(double x, double y) {
    return vector2(x, y);
  }

  public static Vector3d v(double x, double y, double z) {
    return new Vector3d(x, y, z);
  }

  public static Vector3d v(Vector2 v, double z) {
    return new Vector3d(v.x, v.y, z);
  }

  public static Vector3d v0() {
    return v(0, 0, 0);
  }

  public static XAxis x() {
    return Axis.X;
  }

  public static Vector3d vx(double x) {
    return v(x, 0, 0);
  }

  public static YAxis y() {
    return Axis.Y;
  }

  public static Vector3d vy(double y) {
    return v(0, y, 0);
  }

  public static ZAxis z() {
    return Axis.Z;
  }

  public static Vector3d vz(double z) {
    return v(0, 0, z);
  }

  public static <A extends Axis<A>> Alignment<A> alignOutsideMin(A axis) {
    return align(axis.min(), axis.max());
  }

  public static <A extends Axis<A>> Alignment<A> alignOutsideMax(A axis) {
    return align(axis.max(), axis.min());
  }

  public static <A extends Axis<A>> Alignment<A> alignInsideMin(A axis) {
    return align(axis.min(), axis.min());
  }

  public static <A extends Axis<A>> Alignment<A> alignInsideMax(A axis) {
    return align(axis.max(), axis.max());
  }

  public static <A extends Axis<A>> Alignment<A> align(Anchor<A> anchor1, Anchor<A> anchor2) {
    return new Alignment<>(anchor1, anchor2);
  }

  public static Polygon regularPolygon(double radius, int vertexCount) {
    return new RegularPolygon(radius, vertexCount);
  }

  public static Circle circle(double radius) {
    return new Circle(radius);
  }

  public static Rectangle rectangle(double xRange, double yRange) {
    return rectangle(range(xRange), range(yRange));
  }

  public static Rectangle rectangle(Range xRange, Range yRange) {
    return new Rectangle(xRange, yRange, 0);
  }

  public static Polygon convexPolygon(Vector2... vertexes) {
    return new ConvexPolygon(vertexes);
  }

  public static Prism cylinder(double radius, double length) {
    return prism(circle(radius), range(length));
  }

  public static Prism prism(Polygon base, double zRange) {
    return new Prism(base, range(zRange));
  }

  public static Prism prism(Polygon base, Range zRange) {
    return new Prism(base, zRange);
  }

  public static Solid nothing() {
    return new CsgSolid(CSG.fromPolygons());
  }

  public static Box box(double xRange, double yRange, double zRange) {
    return box(range(xRange), range(yRange), range(zRange));
  }

  public static Box box(Range xRange, Range yRange, Range zRange) {
    return new Box(xRange, yRange, zRange);
  }

  public static Solid convexHull(Vector3d... vertexes) {
    return new CsgSolid(hull(asList(vertexes)));
  }

  public static Rod rod() {
    return new Rod();
  }

  public static Pipe pipe() {
    return new Pipe();
  }

  public static Solid quadrupleInZPlane(Solid leg) {
    Solid vxClone = leg.plus(leg.mirror(x()));
    return vxClone.plus(vxClone.mirror(y()));
  }
}
