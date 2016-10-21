package com.mikosik.jsolid.d3;

import eu.mihosoft.vrl.v3d.CSG;

public class CsgSolid extends AbstractSolid {
  private final CSG csg;

  public CsgSolid(CSG csg) {
    this.csg = csg;
  }

  public CSG toCsg() {
    return csg;
  }
}
