package com.mikosik.jsolid.d3.op;

import java.util.List;

import com.mikosik.jsolid.d3.Solid;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Polygon;

public class AddSolid extends AbstractSolid {
  private final Solid solid1;
  private final Solid solid2;

  public AddSolid(Solid solid1, Solid solid2) {
    this.solid1 = solid1;
    this.solid2 = solid2;
  }

  protected List<Polygon> calculateSides() {
    if (solid1.sides().isEmpty()) {
      return solid2.sides();
    }
    if (solid2.sides().isEmpty()) {
      return solid1.sides();
    }
    return CSG.union(solid1.sides(), solid2.sides());
  }
}
