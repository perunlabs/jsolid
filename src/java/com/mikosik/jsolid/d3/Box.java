package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.prism;
import static com.mikosik.jsolid.JSolid.rectangle;

import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Rectangle;

import eu.mihosoft.vrl.v3d.CSG;

public final class Box extends AbstractSolid {
  private final Rectangle base;
  private final Range zRange;

  public Box(Range xRange, Range yRange, Range zRange) {
    this(rectangle(xRange, yRange), zRange);
  }

  private Box(Rectangle rectangle, Range zRange) {
    this.base = rectangle;
    this.zRange = zRange;
  }

  public Box zCornerR(double radius) {
    return new Box(base.cornerR(radius), zRange);
  }

  public CSG toCsg() {
    return prism(base, zRange).toCsg();
  }
}
