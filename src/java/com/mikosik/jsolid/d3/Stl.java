package com.mikosik.jsolid.d3;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import eu.mihosoft.vrl.v3d.Polygon;

public class Stl {
  public static void toStl(Solid solid, Path path) throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(path, CREATE, TRUNCATE_EXISTING)) {
      toStl(solid, writer);
    }
  }

  public static void toStl(Solid solid, Writer writer) throws IOException {
    writer.write("solid \n");
    for (Polygon side : solid.sides()) {
      writeSide(side, writer);
    }
    writer.write("endsolid\n");
  }

  private static void writeSide(Polygon side, Writer writer) throws IOException {
    for (int i = 0; i < side.vertices.size() - 2; i++) {
      writer.write("facet normal ");
      writerVector(side.plane.normal, writer);
      writer.write("  outer loop\n");
      writer.write("    vertex ");
      writerVector(side.vertices.get(0), writer);
      writer.write("    vertex ");
      writerVector(side.vertices.get(i + 1), writer);
      writer.write("    vertex ");
      writerVector(side.vertices.get(i + 2), writer);
      writer.write("  endloop\n");
      writer.write("endfacet\n");
    }
  }

  private static void writerVector(Vector3 position, Writer writer) throws IOException {
    writer.write(Double.toString(position.x));
    writer.write(" ");
    writer.write(Double.toString(position.y));
    writer.write(" ");
    writer.write(Double.toString(position.z));
    writer.write("\n");
  }
}
