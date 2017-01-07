package com.mikosik.jsolid.d3;

import java.util.List;
import java.util.stream.Collectors;

import eu.mihosoft.vrl.v3d.Polygon;

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

  public List<Polygon> sides() {
    return solid.sides()
        .stream()
        .map(p -> p.mul(matrix))
        .collect(Collectors.toList());
  }
}
