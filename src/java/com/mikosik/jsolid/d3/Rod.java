package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.v;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.mikosik.jsolid.d2.Circle;
import com.mikosik.jsolid.util.Check;

import eu.mihosoft.vrl.v3d.CSG;
import eu.mihosoft.vrl.v3d.Polygon;

public final class Rod extends AbstractSolid {
  private final List<Part> parts;

  private static class Part {
    public final double r1;
    public final double r2;
    public final double length;

    public Part(double r1, double r2, double length) {
      this.r1 = r1;
      this.r2 = r2;
      this.length = length;
    }
  }

  public Rod(double radius) {
    this(asList(new Part(0, radius, 0)));
  }

  private Rod(List<Part> rings) {
    this.parts = rings;
  }

  public Rod section(double length) {
    Check.positive(length);
    return funnelTo(length, lastPart().r2);
  }

  public Rod section(double length, double radius) {
    Check.positive(length);
    Check.positive(radius);
    Rod newRod = this;
    if (lastPart().r2 != radius) {
      newRod = funnelTo(0, radius);
    }
    return newRod.funnelTo(length, radius);
  }

  public Rod funnel(double length, double radius) {
    Check.positive(length);
    Check.positive(radius);
    return funnelTo(length, radius);
  }

  private Rod funnelTo(double length, double radius) {
    ArrayList<Part> newRings = new ArrayList<>(parts);
    newRings.add(new Part(lastPart().r2, radius, length));
    return new Rod(newRings);
  }

  private Part lastPart() {
    return parts.get(parts.size() - 1);
  }

  public CSG toCsg() {
    if (parts.size() == 1) {
      throw new IllegalStateException("Cannot create rod without any section.");
    }
    List<Polygon> polygons = new ArrayList<>();
    int vertexCount = vertexCount();
    double z = -length() / 2;
    for (Part part : parts) {
      addPartPolygons(polygons, part, vertexCount, z);
      z = z + part.length;
    }
    addPartPolygons(polygons, new Part(lastPart().r2, 0, 0), vertexCount, z);
    return CSG.fromPolygons(polygons);
  }

  private void addPartPolygons(List<Polygon> polygons, Part part, int vertexCount, double z) {
    double zBottom = z;
    double zTop = z + part.length;

    for (int i = 0; i < vertexCount; i++) {
      double angle1 = vertexIndexToAngle(i, vertexCount);
      double angle2 = vertexIndexToAngle(i + 1, vertexCount);
      double x1Factor = Math.cos(angle1);
      double y1Factor = Math.sin(angle1);
      double x2Factor = Math.cos(angle2);
      double y2Factor = Math.sin(angle2);

      double rBottom = part.r1;
      double rTop = part.r2;

      double x1Bottom = x1Factor * rBottom;
      double x2Bottom = x2Factor * rBottom;
      double x1Top = x1Factor * rTop;
      double x2Top = x2Factor * rTop;

      double y1Bottom = y1Factor * rBottom;
      double y2Bottom = y2Factor * rBottom;
      double y1Top = y1Factor * rTop;
      double y2Top = y2Factor * rTop;

      if (rBottom != 0) {
        polygons.add(new Polygon(
            v(x1Bottom, y1Bottom, zBottom),
            v(x2Bottom, y2Bottom, zBottom),
            v(x1Top, y1Top, zTop)));
      }
      if (rTop != 0) {
        polygons.add(new Polygon(
            v(x2Bottom, y2Bottom, zBottom),
            v(x2Top, y2Top, zTop),
            v(x1Top, y1Top, zTop)));
      }
    }
  }

  private double vertexIndexToAngle(int i, int vertexCount) {
    if (i == vertexCount) {
      return 0.0;
    } else {
      return 2 * Math.PI * i / vertexCount;
    }
  }

  private double length() {
    return parts.stream()
        .mapToDouble(p -> p.length)
        .sum();
  }

  private int vertexCount() {
    double maxRadius = parts.stream()
        .mapToDouble(p -> p.r1)
        .max()
        .orElse(0);
    return Circle.vertexCount(maxRadius);
  }
}
