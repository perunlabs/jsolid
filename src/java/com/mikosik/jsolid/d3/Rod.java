package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.vz;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Vector3d;

public class Rod extends AbstractSolid {
  private double lastRadius;
  private Solid rod;
  private double totalHeight;

  public Rod() {}

  public Rod section(double height) {
    return section(height, lastRadius);
  }

  public Rod section(double height, double radius) {
    return add(height, radius, radius);
  }

  public Rod funnel(double height, double radius) {
    return add(height, lastRadius, radius);
  }

  private Rod add(double height, double radiusFrom, double radiusTo) {
    if (height != 0) {
      Vector3d bottomCenter = vz(totalHeight);
      Vector3d top = bottomCenter.plus(vz(height));
      CSG cylinder = new eu.mihosoft.vrl.v3d.Cylinder(
          bottomCenter, top, radiusFrom, radiusTo, Pipe.slices(radiusFrom, radiusTo)).toCSG();
      Solid next = new CsgSolid(cylinder);
      if (rod == null) {
        rod = next;
      } else {
        rod = rod.plus(next);
      }
    }
    lastRadius = radiusTo;
    totalHeight += height;
    return this;
  }

  public CSG toCsg() {
    return rod.toCsg();
  }
}
