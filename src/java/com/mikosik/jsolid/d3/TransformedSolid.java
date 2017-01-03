package com.mikosik.jsolid.d3;

import eu.mihosoft.vrl.v3d.CSG;

public class TransformedSolid extends AbstractSolid {
  private final Solid solid;
  private final Matrix4 matrix;

  public TransformedSolid(Solid solid, Matrix4 matrix) {
    this.solid = solid;
    this.matrix = matrix;
  }

  public Solid apply(Matrix4 matrix) {
    return new TransformedSolid(solid, matrix.mul(this.matrix));
  }

  public CSG toCsg() {
    return solid.toCsg().apply(matrix);
  }
}
