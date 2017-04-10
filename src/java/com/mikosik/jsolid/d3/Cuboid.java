package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.prism;
import static com.mikosik.jsolid.JSolid.prismXZ;
import static com.mikosik.jsolid.JSolid.prismYZ;
import static com.mikosik.jsolid.JSolid.rectangle;
import static com.mikosik.jsolid.JSolid.x;
import static com.mikosik.jsolid.JSolid.y;
import static com.mikosik.jsolid.JSolid.z;
import static java.lang.Double.MAX_VALUE;

import java.util.List;

import com.mikosik.jsolid.d1.Range;
import com.mikosik.jsolid.d2.Rectangle;
import com.mikosik.jsolid.d3.op.AbstractSolid;

import eu.mihosoft.vrl.v3d.Polygon;

public final class Cuboid extends AbstractSolid {
  private final Range xRange;
  private final Range yRange;
  private final Range zRange;
  private final double zRadius;
  private final double yRadius;
  private final double xRadius;

  public Cuboid(Range xRange, Range yRange, Range zRange, double xRadius, double yRadius,
      double zRadius) {
    this.xRange = xRange;
    this.yRange = yRange;
    this.zRange = zRange;
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    this.zRadius = zRadius;
  }

  @Deprecated
  public Cuboid zCornerR(double radius) {
    return cornerR(z(), radius);
  }

  public Cuboid cornerR(Axis<?> axis, double radius) {
    if (radius < 0) {
      throw new IllegalArgumentException("radius must be greater than 0, but is == " + radius);
    }
    if (axis == x()) {
      return new Cuboid(xRange, yRange, zRange, radius, yRadius, zRadius);
    } else if (axis == y()) {
      return new Cuboid(xRange, yRange, zRange, xRadius, radius, zRadius);
    } else if (axis == z()) {
      return new Cuboid(xRange, yRange, zRange, xRadius, yRadius, radius);
    } else {
      throw new IllegalArgumentException("Unknown axis: " + axis);
    }
  }

  @Deprecated
  public Cuboid zCornerRMax() {
    return zCornerR(MAX_VALUE);
  }

  protected List<Polygon> calculateSides() {
    Rectangle base = rectangle(xRange, yRange)
        .cornerR(zRadius);
    Solid prism = prism(base, zRange);
    if (0 < xRadius) {
      Rectangle baseYZ = rectangle(yRange, zRange)
          .cornerR(xRadius);
      prism = prism.intersect(prismYZ(baseYZ, xRange));
    }
    if (0 < yRadius) {
      Rectangle baseXZ = rectangle(xRange, zRange)
          .cornerR(yRadius);
      prism = prism.intersect(prismXZ(baseXZ, yRange));
    }
    return prism.sides();
  }
}
