package com.mikosik.jsolid.d3.op;

import static eu.mihosoft.vrl.v3d.ext.quickhull3d.HullUtil.hullPolygons;

import java.util.List;

import com.mikosik.jsolid.d3.AbstractSolid;
import com.mikosik.jsolid.d3.Solid;

import eu.mihosoft.vrl.v3d.Polygon;

public class ConvexHullSolid extends AbstractSolid {
  private final Solid solid;

  public ConvexHullSolid(Solid solid) {
    this.solid = solid;
  }

  protected List<Polygon> calculateSides() {
    return hullPolygons(solid.vertexes());
  }
}
