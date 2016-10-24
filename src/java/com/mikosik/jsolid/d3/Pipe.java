package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.vz;
import static java.lang.Math.PI;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Vector3d;

public class Pipe extends AbstractSolid {
  private double lastRadius;
  private double lastHoleRadius;
  private Solid pipe;
  private double totalHeight;

  public Pipe() {}

  public Pipe section(double height) {
    return section(height, lastRadius, lastHoleRadius);
  }

  public Pipe section(double height, double radius, double holeRadius) {
    return add(height, radius, radius, holeRadius, holeRadius);
  }

  public Pipe funnel(double height, double radius, double holeRadius) {
    return add(height, lastRadius, radius, lastHoleRadius, holeRadius);
  }

  private Pipe add(double height, double radiusFrom, double radiusTo,
      double holeRadiusFrom, double holeRadiusTo) {
    if (height != 0) {
      Vector3d bottomCenter = vz(totalHeight);
      Vector3d top = bottomCenter.plus(vz(height));
      CSG cylinder = new eu.mihosoft.vrl.v3d.Cylinder(
          bottomCenter, top, radiusFrom, radiusTo, slices(radiusFrom, radiusTo)).toCSG();
      if (holeRadiusFrom != 0 || holeRadiusTo != 0) {
        cylinder = cylinder.difference(new eu.mihosoft.vrl.v3d.Cylinder(
            bottomCenter, top, holeRadiusFrom, holeRadiusTo,
            slices(holeRadiusFrom, holeRadiusTo)).toCSG());
      }
      Solid next = new CsgSolid(cylinder);
      if (pipe == null) {
        pipe = next;
      } else {
        pipe = pipe.plus(next);
      }
    }
    this.lastRadius = radiusTo;
    this.lastHoleRadius = holeRadiusTo;
    totalHeight += height;
    return this;
  }

  public static int slices(double r1, double r2) {
    return requiredSlices(Math.max(r1, r2));
  }

  public static int requiredSlices(double radius) {
    int slices = (int) (4 * 2 * PI * radius);
    return Math.max(10, slices);
  }

  public CSG toCsg() {
    return pipe.toCsg();
  }
}
