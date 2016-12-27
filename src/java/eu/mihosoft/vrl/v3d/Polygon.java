/**
 * Polygon.java
 *
 * Copyright 2014-2014 Michael Hoffer <info@michaelhoffer.de>. All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY Michael Hoffer <info@michaelhoffer.de> "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL Michael Hoffer <info@michaelhoffer.de> OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of Michael Hoffer
 * <info@michaelhoffer.de>.
 */
package eu.mihosoft.vrl.v3d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.mikosik.jsolid.d3.Vector3;

import eu.mihosoft.vrl.v3d.ext.org.poly2tri.PolygonUtil;

/**
 * Represents a convex polygon.
 */
public final class Polygon {
  public List<Vector3> vertices;
  /**
   * Plane defined by this polygon.
   *
   * <b>Note:</b> uses first three vertices to define the plane.
   */
  public final Plane plane;

  /**
   * Decomposes the specified concave polygon into convex polygons.
   *
   * @param points
   *          the points that define the polygon
   * @return the decomposed concave polygon (list of convex polygons)
   */
  public static List<Polygon> fromConcavePoints(Vector3... points) {
    return PolygonUtil.concaveToConvex(fromPoints(points));
  }

  /**
   * Decomposes the specified concave polygon into convex polygons.
   *
   * @param points
   *          the points that define the polygon
   * @return the decomposed concave polygon (list of convex polygons)
   */
  public static List<Polygon> fromConcavePoints(List<Vector3> points) {
    return PolygonUtil.concaveToConvex(fromPoints(points));
  }

  /**
   * Constructor. Creates a new polygon that consists of the specified vertices.
   *
   * <b>Note:</b> the vertices used to initialize a polygon must be coplanar and
   * form a convex loop.
   *
   * @param vertices
   *          polygon vertices
   */
  public Polygon(List<Vector3> vertices) {
    this.vertices = vertices;
    this.plane = Plane.createFromPoints(
        vertices.get(0),
        vertices.get(1),
        vertices.get(2));
  }

  /**
   * Constructor. Creates a new polygon that consists of the specified vertices.
   *
   * <b>Note:</b> the vertices used to initialize a polygon must be coplanar and
   * form a convex loop.
   *
   * @param vertices
   *          polygon vertices
   *
   */
  public Polygon(Vector3... vertices) {
    this(Arrays.asList(vertices));
  }

  @Override
  public Polygon clone() {
    return new Polygon(new ArrayList<>(vertices));
  }

  /**
   * Flips this polygon.
   *
   * @return this polygon
   */
  public Polygon flip() {
    Collections.reverse(vertices);
    plane.flip();
    return this;
  }

  /**
   * Returns a flipped copy of this polygon.
   *
   * <b>Note:</b> this polygon is not modified.
   *
   * @return a flipped copy of this polygon
   */
  public Polygon flipped() {
    return clone().flip();
  }

  /**
   * Returns this polygon in STL string format.
   *
   * @return this polygon in STL string format
   */
  public String toStlString() {
    return toStlString(new StringBuilder()).toString();
  }

  /**
   * Returns this polygon in STL string format.
   *
   * @param sb
   *          string builder
   *
   * @return the specified string builder
   */
  public StringBuilder toStlString(StringBuilder sb) {
    if (this.vertices.size() >= 3) {

      // TODO: improve the triangulation?
      //
      // STL requires triangular polygons.
      // If our polygon has more vertices, create
      // multiple triangles:
      String firstVertexStl = toVertexStl(vertices.get(0));
      for (int i = 0; i < this.vertices.size() - 2; i++) {
        sb
            .append("  facet normal ")
            .append(toStl(plane.normal))
            .append("\n")
            .append("    outer loop\n")
            .append("      ")
            .append(firstVertexStl)
            .append("\n")
            .append("      ")
            .append(toVertexStl(vertices.get(i + 1)))
            .append("\n").append("      ")
            .append(toVertexStl(vertices.get(i + 2)))
            .append("\n")
            .append("    endloop\n")
            .append("  endfacet\n");
      }
    }

    return sb;
  }

  private static String toVertexStl(Vector3 v) {
    return "vertex " + toStl(v);
  }

  private static String toStl(Vector3 position) {
    return position.x + " " + position.y + " " + position.z;
  }

  /**
   * Translates this polygon.
   *
   * @param v
   *          the vector that defines the translation
   * @return this polygon
   */
  public Polygon translate(Vector3 v) {
    vertices = vertices.stream()
        .map((vertex) -> vertex.plus(v))
        .collect(Collectors.toList());

    Vector3 a = this.vertices.get(0);
    Vector3 b = this.vertices.get(1);
    Vector3 c = this.vertices.get(2);

    this.plane.normal = b.minus(a).cross(c.minus(a));

    return this;
  }

  /**
   * Returns a translated copy of this polygon.
   *
   * <b>Note:</b> this polygon is not modified
   *
   * @param v
   *          the vector that defines the translation
   *
   * @return a translated copy of this polygon
   */
  public Polygon translated(Vector3 v) {
    return clone().translate(v);
  }

  /**
   * Applies the specified transformation to this polygon.
   *
   * <b>Note:</b> if the applied transformation performs a mirror operation the
   * vertex order of this polygon is reversed.
   *
   * @param transform
   *          the transformation to apply
   *
   * @return this polygon
   */
  public Polygon transform(Transform transform) {
    vertices = vertices.stream()
        .map((vertex) -> transform.mul(vertex))
        .collect(Collectors.toList());

    Vector3 a = this.vertices.get(0);
    Vector3 b = this.vertices.get(1);
    Vector3 c = this.vertices.get(2);

    this.plane.normal = b.minus(a).cross(c.minus(a)).normalize();
    this.plane.dist = this.plane.normal.dot(a);

    if (transform.isMirror()) {
      // the transformation includes mirroring. flip polygon
      flip();
    }
    return this;
  }

  /**
   * Returns a transformed copy of this polygon.
   *
   * <b>Note:</b> if the applied transformation performs a mirror operation the
   * vertex order of this polygon is reversed.
   *
   * <b>Note:</b> this polygon is not modified
   *
   * @param transform
   *          the transformation to apply
   * @return a transformed copy of this polygon
   */
  public Polygon transformed(Transform transform) {
    return clone().transform(transform);
  }

  /**
   * Creates a polygon from the specified points.
   *
   * @param points
   *          the points that define the polygon
   * @return a polygon defined by the specified point list
   */
  public static Polygon fromPoints(Vector3... points) {
    return fromPoints(Arrays.asList(points));
  }

  /**
   * Creates a polygon from the specified point list.
   *
   * @param points
   *          the points that define the polygon
   * @return a polygon defined by the specified point list
   */
  public static Polygon fromPoints(
      List<Vector3> points) {
    return new Polygon(new ArrayList<>(points));
  }

  public boolean contains(Vector3 p) {
    // taken from http://www.java-gaming.org/index.php?topic=26013.0
    // and
    // http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
    double px = p.x;
    double py = p.y;
    boolean oddNodes = false;
    double x2 = vertices.get(vertices.size() - 1).x;
    double y2 = vertices.get(vertices.size() - 1).y;
    double x1, y1;
    for (int i = 0; i < vertices.size(); x2 = x1, y2 = y1, ++i) {
      x1 = vertices.get(i).x;
      y1 = vertices.get(i).y;
      if (((y1 < py) && (y2 >= py))
          || (y1 >= py) && (y2 < py)) {
        if ((py - y1) / (y2 - y1)
            * (x2 - x1) < (px - x1)) {
          oddNodes = !oddNodes;
        }
      }
    }
    return oddNodes;
  }

  public boolean contains(Polygon p) {
    for (Vector3 v : p.vertices) {
      if (!contains(v)) {
        return false;
      }
    }

    return true;
  }
}
