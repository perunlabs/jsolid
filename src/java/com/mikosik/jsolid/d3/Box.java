package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.Solids.prism;
import static com.mikosik.jsolid.Solids.range;
import static com.mikosik.jsolid.Solids.rectangle;
import static java.util.Objects.requireNonNull;

import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Rectangle;

import eu.mihosoft.vrl.v3d.CSG;

public class Box extends AbstractSolid {
  private final Rectangle base;
  private final Range zd;

  public Box() {
    this.base = rectangle();
    this.zd = range();
  }

  private Box(Rectangle base, Range zd) {
    this.base = requireNonNull(base);
    this.zd = zd;
  }

  public Box xd(double xd) {
    return new Box(base.xd(xd), zd);
  }

  public Box xd(Range xd) {
    return new Box(base.xd(xd), zd);
  }

  public Box yd(double yd) {
    return new Box(base.yd(yd), zd);
  }

  public Box yd(Range yd) {
    return new Box(base.yd(yd), zd);
  }

  public Box zd(double zd) {
    return new Box(base, this.zd.vd(zd));
  }

  public Box zd(Range zd) {
    return new Box(base, zd);
  }

  public Box x(double x1, double x2) {
    return new Box(base.x(x1, x2), zd);
  }

  public Box y(double y1, double y2) {
    return new Box(base.y(y1, y2), zd);
  }

  public Box z(double z1, double z2) {
    return new Box(base, zd.v(z1, z2));
  }

  public Box x1(double x1) {
    return new Box(base.x1(x1), zd);
  }

  public Box x2(double x2) {
    return new Box(base.x2(x2), zd);
  }

  public Box y1(double y1) {
    return new Box(base.y1(y1), zd);
  }

  public Box y2(double y2) {
    return new Box(base.y2(y2), zd);
  }

  public Box z1(double z1) {
    return new Box(base, zd.v1(z1));
  }

  public Box z2(double z2) {
    return new Box(base, zd.v2(z2));
  }

  public Box zCornerR(double radius) {
    return new Box(base.cornerR(radius), zd);
  }

  public CSG toCsg() {
    return prism(base, zd).toCsg();
  }
}
