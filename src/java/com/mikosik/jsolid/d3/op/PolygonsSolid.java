package com.mikosik.jsolid.d3.op;

import static com.mikosik.jsolid.util.Lists.immutable;

import java.util.List;

import eu.mihosoft.vrl.v3d.Polygon;

public class PolygonsSolid extends AbstractSolid {
  private final List<Polygon> sides;

  public PolygonsSolid(List<Polygon> sides) {
    this.sides = immutable(sides);
  }

  protected List<Polygon> calculateSides() {
    return sides;
  }
}