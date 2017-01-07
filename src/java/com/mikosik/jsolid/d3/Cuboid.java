package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.prism;
import static com.mikosik.jsolid.JSolid.rectangle;

import java.util.List;

import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Rectangle;

import eu.mihosoft.vrl.v3d.Polygon;

public final class Cuboid extends AbstractSolid {
  private final Rectangle base;
  private final Range zRange;

  public Cuboid(Range xRange, Range yRange, Range zRange) {
    this(rectangle(xRange, yRange), zRange);
  }

  private Cuboid(Rectangle rectangle, Range zRange) {
    this.base = rectangle;
    this.zRange = zRange;
  }

  public Cuboid zCornerR(double radius) {
    return new Cuboid(base.cornerR(radius), zRange);
  }

  public Cuboid zCornerRMax() {
    return new Cuboid(base.cornerRMax(), zRange);
  }

  public List<Polygon> sides() {
    return prism(base, zRange).sides();
  }
}
