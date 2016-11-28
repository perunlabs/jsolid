package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.prism;
import static com.mikosik.jsolid.JSolid.range;
import static com.mikosik.jsolid.JSolid.rectangle;
import static java.util.Objects.requireNonNull;

import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Rectangle;

import eu.mihosoft.vrl.v3d.CSG;

public final class Box extends AbstractSolid {
  private final Rectangle base;
  private final Range zRange;

  public Box() {
    this.base = rectangle();
    this.zRange = range();
  }

  private Box(Rectangle base, Range zRange) {
    this.base = requireNonNull(base);
    this.zRange = zRange;
  }

  public Box x(Range range) {
    return new Box(base.xd(range), zRange);
  }

  public Box y(Range range) {
    return new Box(base.yd(range), zRange);
  }

  public Box z(Range range) {
    return new Box(base, range);
  }

  public Box zCornerR(double radius) {
    return new Box(base.cornerR(radius), zRange);
  }

  public CSG toCsg() {
    return prism(base, zRange).toCsg();
  }
}
