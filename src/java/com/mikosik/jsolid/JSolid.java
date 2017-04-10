package com.mikosik.jsolid;

import static com.mikosik.jsolid.d2.Vector2.vector2;
import static com.mikosik.jsolid.d3.Vector3.vector3;
import static eu.mihosoft.vrl.v3d.ext.quickhull3d.HullUtil.hullPolygons;
import static java.util.Arrays.asList;

import java.util.List;

import com.mikosik.jsolid.d1.Anchor1;
import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Circle;
import com.mikosik.jsolid.d2.ConvexPolygon;
import com.mikosik.jsolid.d2.Polygon;
import com.mikosik.jsolid.d2.Rectangle;
import com.mikosik.jsolid.d2.RegularPolygon;
import com.mikosik.jsolid.d2.Vector2;
import com.mikosik.jsolid.d3.Alignment;
import com.mikosik.jsolid.d3.Anchor3;
import com.mikosik.jsolid.d3.Anchor3.EdgeAnchor;
import com.mikosik.jsolid.d3.Axis;
import com.mikosik.jsolid.d3.Axis.XAxis;
import com.mikosik.jsolid.d3.Axis.YAxis;
import com.mikosik.jsolid.d3.Axis.ZAxis;
import com.mikosik.jsolid.d3.Cuboid;
import com.mikosik.jsolid.d3.Cylinder;
import com.mikosik.jsolid.d3.Matrix4;
import com.mikosik.jsolid.d3.Prism;
import com.mikosik.jsolid.d3.Solid;
import com.mikosik.jsolid.d3.Vector3;
import com.mikosik.jsolid.d3.op.SolidImpl;

public class JSolid {
  private static final Config CONFIG = new Config();

  public static Config config() {
    return CONFIG;
  }

  public static Anchor1 min() {
    return Anchor1.MIN;
  }

  public static Anchor1 max() {
    return Anchor1.MAX;
  }

  public static Anchor1 center() {
    return Anchor1.CENTER;
  }

  public static Range range(double length) {
    return new Range(length);
  }

  public static Range range(double v1, double v2) {
    return new Range(v1, v2);
  }

  public static Vector2 v(double x, double y) {
    return vector2(x, y);
  }

  public static Vector3 v(double x, double y, double z) {
    return vector3(x, y, z);
  }

  public static Vector3 v(Vector2 v, double z) {
    return vector3(v.x, v.y, z);
  }

  public static Vector3 v0() {
    return v(0, 0, 0);
  }

  public static XAxis x() {
    return Axis.X;
  }

  public static Vector3 vx(double x) {
    return v(x, 0, 0);
  }

  public static YAxis y() {
    return Axis.Y;
  }

  public static Vector3 vy(double y) {
    return v(0, y, 0);
  }

  public static ZAxis z() {
    return Axis.Z;
  }

  public static Vector3 vz(double z) {
    return v(0, 0, z);
  }

  public static Matrix4 matrix(
      double m11, double m12, double m13, double m14,
      double m21, double m22, double m23, double m24,
      double m31, double m32, double m33, double m34,
      double m41, double m42, double m43, double m44) {
    return new Matrix4(
        m11, m12, m13, m14,
        m21, m22, m23, m24,
        m31, m32, m33, m34,
        m41, m42, m43, m44);
  }

  public static <A extends Axis<A>> Alignment alignOutside(EdgeAnchor<A> anchor) {
    return align(anchor, anchor.other());
  }

  public static <A extends Axis<A>> Alignment alignOutside(EdgeAnchor<A> anchor, double margin) {
    return align(anchor, margin, anchor.other());
  }

  public static <A extends Axis<A>> Alignment align(EdgeAnchor<A> anchor) {
    return align(anchor, anchor);
  }

  public static <A extends Axis<A>> Alignment align(EdgeAnchor<A> anchor, double margin) {
    return align(anchor, margin, anchor);
  }

  public static <A extends Axis<A>> Alignment align(Anchor3<A> anchor1, Anchor3<A> anchor2) {
    return new Alignment(anchor1, anchor2, 0);
  }

  public static <A extends Axis<A>> Alignment align(EdgeAnchor<A> anchor1, double margin,
      EdgeAnchor<A> anchor2) {
    return align(anchor1, margin, (Anchor3<A>) anchor2);
  }

  public static <A extends Axis<A>> Alignment align(EdgeAnchor<A> anchor1, double margin,
      Anchor3<A> anchor2) {
    return new Alignment(anchor1, anchor2, anchor1.sign() * margin);
  }

  public static <A extends Axis<A>> Alignment align(Anchor3<A> anchor1, double margin,
      EdgeAnchor<A> anchor2) {
    return new Alignment(anchor1, anchor2, -anchor2.sign() * margin);
  }

  public static EdgeAnchor<XAxis> maxX() {
    return x().max();
  }

  public static EdgeAnchor<XAxis> minX() {
    return x().min();
  }

  public static EdgeAnchor<YAxis> maxY() {
    return y().max();
  }

  public static EdgeAnchor<YAxis> minY() {
    return y().min();
  }

  public static EdgeAnchor<ZAxis> maxZ() {
    return z().max();
  }

  public static EdgeAnchor<ZAxis> minZ() {
    return z().min();
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

  public static Rectangle rectangle(double xRange, Range yRange) {
    return rectangle(range(xRange), yRange);
  }

  public static Rectangle rectangle(Range xRange, double yRange) {
    return rectangle(xRange, range(yRange));
  }

  public static Rectangle rectangle(Range xRange, Range yRange) {
    return new Rectangle(xRange, yRange, 0);
  }

  public static Polygon convexPolygon(Vector2... vertexes) {
    return new ConvexPolygon(vertexes);
  }

  public static Solid prismXZ(Polygon xzBase, double yRange) {
    return prismXZ(xzBase, range(yRange));
  }

  public static Solid prismXZ(Polygon base, Range yRange) {
    return prism(base, yRange).rotate(x(), 90);
  }

  public static Solid prismYZ(Polygon yzBase, double xRange) {
    return prismYZ(yzBase, range(xRange));
  }

  public static Solid prismYZ(Polygon base, Range xRange) {
    return prism(base, xRange).rotate(z(), 90).rotate(y(), 90);
  }

  public static Prism prism(Polygon base, double zRange) {
    return new Prism(base, range(zRange));
  }

  public static Prism prism(Polygon base, Range zRange) {
    return new Prism(base, zRange);
  }

  public static Solid nothing() {
    return new SolidImpl(asList());
  }

  public static Cuboid cuboid(double xRange, double yRange, double zRange) {
    return cuboid(range(xRange), range(yRange), range(zRange));
  }

  public static Cuboid cuboid(Range xRange, double yRange, double zRange) {
    return cuboid(xRange, range(yRange), range(zRange));
  }

  public static Cuboid cuboid(double xRange, Range yRange, double zRange) {
    return cuboid(range(xRange), yRange, range(zRange));
  }

  public static Cuboid cuboid(double xRange, double yRange, Range zRange) {
    return cuboid(range(xRange), range(yRange), zRange);
  }

  public static Cuboid cuboid(double xRange, Range yRange, Range zRange) {
    return cuboid(range(xRange), yRange, zRange);
  }

  public static Cuboid cuboid(Range xRange, double yRange, Range zRange) {
    return cuboid(xRange, range(yRange), zRange);
  }

  public static Cuboid cuboid(Range xRange, Range yRange, double zRange) {
    return cuboid(xRange, yRange, range(zRange));
  }

  public static Cuboid cuboid(Range xRange, Range yRange, Range zRange) {
    return new Cuboid(xRange, yRange, zRange, 0, 0, 0);
  }

  public static Solid convexHull(Vector3... vertexes) {
    return convexHull(asList(vertexes));
  }

  public static Solid convexHull(List<Vector3> vertexes) {
    return new SolidImpl(hullPolygons(vertexes));
  }

  public static Cylinder cylinder(double radius, double length) {
    return new Cylinder(radius).addSegment(length);
  }

  public static Cylinder funnel(double radiusStart, double radiusEnd, double length) {
    return new Cylinder(radiusStart).addFunnel(radiusEnd, length);
  }
}
