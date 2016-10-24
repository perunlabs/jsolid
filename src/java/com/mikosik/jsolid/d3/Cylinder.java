package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.circle;
import static com.mikosik.jsolid.JSolid.prism;
import static com.mikosik.jsolid.JSolid.range;

import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Circle;

import eu.mihosoft.vrl.v3d.CSG;

public class Cylinder extends AbstractSolid {
  private final Circle base;
  private final Range zd;

  public Cylinder() {
    this.base = null;
    this.zd = range();
  }

  public Cylinder(Circle base, Range zd) {
    this.base = base;
    this.zd = zd;
  }

  public Cylinder r(double radius) {
    return new Cylinder(circle(radius), zd);
  }

  public Cylinder zd(double zd) {
    return new Cylinder(base, this.zd.vd(zd));
  }

  public Cylinder zd(Range zd) {
    return new Cylinder(base, zd);
  }

  public Cylinder z(double z1, double z2) {
    return new Cylinder(base, zd.v(z1, z2));
  }

  public Cylinder z1(double z1) {
    return new Cylinder(base, zd.v1(z1));
  }

  public Cylinder z2(double z2) {
    return new Cylinder(base, zd.v2(z2));
  }

  public CSG toCsg() {
    return prism(base, zd).toCsg();
  }
}
