package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.d2.Vector2.vector2;
import static com.perunlabs.jsolid.d3.Vector3.vector3;
import static eu.mihosoft.vrl.v3d.ext.quickhull3d.HullUtil.hullPolygons;
import static java.util.Arrays.asList;

import java.util.List;

import com.perunlabs.jsolid.d1.Anchor1;
import com.perunlabs.jsolid.d1.Range;
import com.perunlabs.jsolid.d2.Circle;
import com.perunlabs.jsolid.d2.ConvexPolygon;
import com.perunlabs.jsolid.d2.Polygon;
import com.perunlabs.jsolid.d2.Rectangle;
import com.perunlabs.jsolid.d2.RegularPolygon;
import com.perunlabs.jsolid.d2.Vector2;
import com.perunlabs.jsolid.d3.Alignment;
import com.perunlabs.jsolid.d3.Anchor3;
import com.perunlabs.jsolid.d3.Axis;
import com.perunlabs.jsolid.d3.Axis.XAxis;
import com.perunlabs.jsolid.d3.Axis.YAxis;
import com.perunlabs.jsolid.d3.Axis.ZAxis;
import com.perunlabs.jsolid.d3.Cuboid;
import com.perunlabs.jsolid.d3.Cylinder;
import com.perunlabs.jsolid.d3.Edge;
import com.perunlabs.jsolid.d3.Matrix4;
import com.perunlabs.jsolid.d3.Prism;
import com.perunlabs.jsolid.d3.Solid;
import com.perunlabs.jsolid.d3.Vector3;
import com.perunlabs.jsolid.d3.op.PolygonsSolid;

public class JSolid {
  private static final Config CONFIG = new Config();

  public static Config config() {
    return CONFIG;
  }

  public static Anchor1 min() {
    return Anchor1.min();
  }

  public static Anchor1 max() {
    return Anchor1.max();
  }

  public static Anchor1 center() {
    return Anchor1.center();
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

  public static Edge edge(Vector3 pointA, Vector3 pointB) {
    return new Edge(pointA, pointB);
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

  public static <A extends Axis<A>> Alignment alignOutside(Anchor3<A> anchor) {
    return align(anchor, anchor.other());
  }

  public static <A extends Axis<A>> Alignment alignOutside(Anchor3<A> anchor, double margin) {
    return align(anchor, margin, anchor.other());
  }

  public static <A extends Axis<A>> Alignment align(Anchor3<A> anchor) {
    return align(anchor, anchor);
  }

  public static <A extends Axis<A>> Alignment align(Anchor3<A> anchor, double margin) {
    return align(anchor, margin, anchor);
  }

  public static <A extends Axis<A>> Alignment align(Anchor3<A> anchorA, Anchor3<A> anchorB) {
    return new Alignment(anchorA, anchorB, 0);
  }

  public static <A extends Axis<A>> Alignment align(Anchor3<A> anchorA, double margin,
      Anchor3<A> anchorB) {
    if (anchorA.edge() != 0) {
      return new Alignment(anchorA, anchorB, anchorA.edge() * margin);
    } else if (anchorB.edge() != 0) {
      return new Alignment(anchorA, anchorB, -anchorB.edge() * margin);
    } else {
      throw new IllegalArgumentException("Cannot align using two anchors without sign.");
    }
  }

  public static Anchor3<XAxis> maxX() {
    return max().on(x());
  }

  public static Anchor3<XAxis> centerX() {
    return center().on(x());
  }

  public static Anchor3<XAxis> minX() {
    return min().on(x());
  }

  public static Anchor3<YAxis> maxY() {
    return max().on(y());
  }

  public static Anchor3<YAxis> centerY() {
    return center().on(y());
  }

  public static Anchor3<YAxis> minY() {
    return min().on(y());
  }

  public static Anchor3<ZAxis> maxZ() {
    return max().on(z());
  }

  public static Anchor3<ZAxis> centerZ() {
    return center().on(z());
  }

  public static Anchor3<ZAxis> minZ() {
    return min().on(z());
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

  public static Solid prismXZ(Polygon xzBase, Range yRange) {
    return prism(xzBase, yRange.neg()).rotate(x(), 90);
  }

  public static Solid prismYZ(Polygon yzBase, double xRange) {
    return prismYZ(yzBase, range(xRange));
  }

  public static Solid prismYZ(Polygon yzBase, Range xRange) {
    return prism(yzBase, xRange).rotate(z(), 90).rotate(y(), 90);
  }

  public static Prism prism(Polygon base, double zRange) {
    return new Prism(base, range(zRange));
  }

  public static Prism prism(Polygon base, Range zRange) {
    return new Prism(base, zRange);
  }

  public static Solid nothing() {
    return new PolygonsSolid(asList());
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
    return new PolygonsSolid(hullPolygons(vertexes));
  }

  public static Cylinder cylinder(double radius, double length) {
    return new Cylinder(radius).addSegment(length);
  }

  public static Cylinder funnel(double radiusStart, double radiusEnd, double length) {
    return new Cylinder(radiusStart).addFunnel(radiusEnd, length);
  }
}
