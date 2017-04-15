package com.mikosik.jsolid.d3.op;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.mikosik.jsolid.d3.Alignment;
import com.mikosik.jsolid.d3.Solid;

import eu.mihosoft.vrl.v3d.Polygon;

public class AlignSolid extends AbstractSolid {
  private final Solid solid;
  private final Solid aligning;
  private final List<Alignment> alignments;

  public AlignSolid(Solid solid, Solid aligning, List<Alignment> alignments) {
    this.solid = solid;
    this.aligning = aligning;
    this.alignments = new ArrayList<>(alignments);
  }

  protected List<Polygon> calculateSides() {
    if (solid.sides().isEmpty()) {
      return asList();
    }
    Solid result = solid;
    for (Alignment alignment : alignments) {
      result = alignment.align(aligning, solid);
    }
    return result.sides();
  }
}
