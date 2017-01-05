/**
 * CSG.java
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mikosik.jsolid.d3.Matrix4;

import eu.mihosoft.vrl.v3d.ext.quickhull3d.HullUtil;

/**
 * Constructive Solid Geometry (CSG).
 *
 * This implementation is a Java port of <a href=
 * "https://github.com/evanw/csg.js/">https://github.com/evanw/csg.js/</a> with
 * some additional features like polygon extrude, transformations etc. Thanks to
 * the author for creating the CSG.js library.<br>
 * <br>
 *
 * <b>Implementation Details</b>
 *
 * All CSG operations are implemented in terms of two functions,
 * {@link Node#clipTo(eu.mihosoft.vrl.v3d.Node)} and {@link Node#invert()},
 * which remove parts of a BSP tree inside another BSP tree and swap solid and
 * empty space, respectively. To find the union of {@code a} and {@code b}, we
 * want to remove everything in {@code a} inside {@code b} and everything in
 * {@code b} inside {@code a}, then combine polygons from {@code a} and
 * {@code b} into one solid:
 *
 * <blockquote>
 *
 * <pre>
 * a.clipTo(b);
 * b.clipTo(a);
 * a.build(b.allPolygons());
 * </pre>
 *
 * </blockquote>
 *
 * The only tricky part is handling overlapping coplanar polygons in both trees.
 * The code above keeps both copies, but we need to keep them in one tree and
 * remove them in the other tree. To remove them from {@code b} we can clip the
 * inverse of {@code b} against {@code a}. The code for union now looks like
 * this:
 *
 * <blockquote>
 *
 * <pre>
 * a.clipTo(b);
 * b.clipTo(a);
 * b.invert();
 * b.clipTo(a);
 * b.invert();
 * a.build(b.allPolygons());
 * </pre>
 *
 * </blockquote>
 *
 * Subtraction and intersection naturally follow from set operations. If union
 * is {@code A | B}, differenceion is {@code A - B = ~(~A | B)} and intersection
 * is {@code A & B =
 * ~(~A | ~B)} where {@code ~} is the complement operator.
 */
public class CSG {
  private List<Polygon> polygons;

  private CSG() {}

  /**
   * Constructs a CSG from a list of {@link Polygon} instances.
   *
   * @param polygons
   *          polygons
   * @return a CSG instance
   */
  public static CSG fromPolygons(List<Polygon> polygons) {
    CSG csg = new CSG();
    csg.polygons = polygons;
    return csg;
  }

  /**
   * Constructs a CSG from the specified {@link Polygon} instances.
   *
   * @param polygons
   *          polygons
   * @return a CSG instance
   */
  public static CSG fromPolygons(Polygon... polygons) {
    return fromPolygons(Arrays.asList(polygons));
  }

  @Override
  public CSG clone() {
    CSG csg = new CSG();
    Stream<Polygon> polygonStream;
    if (polygons.size() > 200) {
      polygonStream = polygons.parallelStream();
    } else {
      polygonStream = polygons.stream();
    }

    csg.polygons = polygonStream.map((Polygon p) -> p.clone()).collect(Collectors.toList());
    return csg;
  }

  /**
   *
   * @return the polygons of this CSG
   */
  public List<Polygon> getPolygons() {
    return polygons;
  }

  /**
   * Return a new CSG solid representing the union of this csg and the specified
   * csg.
   *
   * <blockquote>
   *
   * <pre>
   *    A.union(B)
   *
   *    +-------+            +-------+
   *    |       |            |       |
   *    |   A   |            |       |
   *    |    +--+----+   =   |       +----+
   *    +----+--+    |       +----+       |
   *         |   B   |            |       |
   *         |       |            |       |
   *         +-------+            +-------+
   * </pre>
   *
   * </blockquote>
   *
   *
   * @param csg
   *          other csg
   *
   * @return union of this csg and the specified csg
   */
  public CSG union(CSG csg) {
    return CSG.fromPolygons(union(this.clone().polygons, csg.clone().polygons));
  }

  public static List<Polygon> union(List<Polygon> polygons1, List<Polygon> polygons2) {
    Node a = new Node(polygons1);
    Node b = new Node(polygons2);
    a.clipTo(b);
    b.clipTo(a);
    b.invert();
    b.clipTo(a);
    b.invert();
    a.build(b.allPolygons());
    return a.allPolygons();
  }

  /**
   * Returns a csg consisting of the polygons of this csg and the specified csg.
   *
   * The purpose of this method is to allow fast union operations for objects
   * that do not intersect.
   *
   * <p>
   * <b>WARNING:</b> this method does not apply the csg algorithms. Therefore,
   * please ensure that this csg and the specified csg do not intersect.
   *
   * @param csg
   *          csg
   *
   * @return a csg consisting of the polygons of this csg and the specified csg
   */
  public CSG dumbUnion(CSG csg) {
    CSG result = this.clone();
    CSG other = csg.clone();
    result.polygons.addAll(other.polygons);
    return result;
  }

  /**
   * Returns the convex hull of this csg.
   *
   * @return the convex hull of this csg
   */
  public CSG hull() {
    return HullUtil.hull(this);
  }

  /**
   * Returns the convex hull of this csg and the union of the specified csgs.
   *
   * @param csgs
   *          csgs
   * @return the convex hull of this csg and the specified csgs
   */
  public CSG hull(List<CSG> csgs) {
    CSG csgsUnion = new CSG();
    csgsUnion.polygons = this.clone().polygons;

    csgs.stream().forEach((csg) -> {
      csgsUnion.polygons.addAll(csg.clone().polygons);
    });

    return csgsUnion.hull();
  }

  /**
   * Returns the convex hull of this csg and the union of the specified csgs.
   *
   * @param csgs
   *          csgs
   * @return the convex hull of this csg and the specified csgs
   */
  public CSG hull(CSG... csgs) {
    return hull(Arrays.asList(csgs));
  }

  /**
   * Return a new CSG solid representing the difference of this csg and the
   * specified csgs.
   *
   * <blockquote>
   *
   * <pre>
   * A.difference(B)
   *
   * +-------+            +-------+
   * |       |            |       |
   * |   A   |            |       |
   * |    +--+----+   =   |    +--+
   * +----+--+    |       +----+
   *      |   B   |
   *      |       |
   *      +-------+
   * </pre>
   *
   * </blockquote>
   *
   * @param csgs
   *          other csgs
   * @return difference of this csg and the specified csgs
   */
  public CSG difference(List<CSG> csgs) {

    if (csgs.isEmpty()) {
      return this.clone();
    }

    CSG csgsUnion = csgs.get(0);

    for (int i = 1; i < csgs.size(); i++) {
      csgsUnion = csgsUnion.union(csgs.get(i));
    }

    return difference(csgsUnion);
  }

  /**
   * Return a new CSG solid representing the difference of this csg and the
   * specified csgs.
   *
   * <blockquote>
   *
   * <pre>
   * A.difference(B)
   *
   * +-------+            +-------+
   * |       |            |       |
   * |   A   |            |       |
   * |    +--+----+   =   |    +--+
   * +----+--+    |       +----+
   *      |   B   |
   *      |       |
   *      +-------+
   * </pre>
   *
   * </blockquote>
   *
   * @param csgs
   *          other csgs
   * @return difference of this csg and the specified csgs
   */
  public CSG difference(CSG... csgs) {

    return difference(Arrays.asList(csgs));
  }

  /**
   * Return a new CSG solid representing the difference of this csg and the
   * specified csg.
   *
   * <blockquote>
   *
   * <pre>
   * A.difference(B)
   *
   * +-------+            +-------+
   * |       |            |       |
   * |   A   |            |       |
   * |    +--+----+   =   |    +--+
   * +----+--+    |       +----+
   *      |   B   |
   *      |       |
   *      +-------+
   * </pre>
   *
   * </blockquote>
   *
   * @param csg
   *          other csg
   * @return difference of this csg and the specified csg
   */
  public CSG difference(CSG csg) {
    return CSG.fromPolygons(difference(this.clone().polygons, csg.clone().polygons));
  }

  public static List<Polygon> difference(List<Polygon> polygons1, List<Polygon> polygons2) {
    Node a = new Node(polygons1);
    Node b = new Node(polygons2);
    a.invert();
    a.clipTo(b);
    b.clipTo(a);
    b.invert();
    b.clipTo(a);
    b.invert();
    a.build(b.allPolygons());
    a.invert();
    return a.allPolygons();
  }

  /**
   * Return a new CSG solid representing the intersection of this csg and the
   * specified csg.
   *
   * <blockquote>
   *
   * <pre>
   *     A.intersect(B)
   *
   *     +-------+
   *     |       |
   *     |   A   |
   *     |    +--+----+   =   +--+
   *     +----+--+    |       +--+
   *          |   B   |
   *          |       |
   *          +-------+
   * }
   * </pre>
   *
   * </blockquote>
   *
   * @param csg
   *          other csg
   * @return intersection of this csg and the specified csg
   */
  public CSG intersect(CSG csg) {
    return CSG.fromPolygons(intersect(this.clone().polygons, csg.clone().polygons));
  }

  public static List<Polygon> intersect(List<Polygon> polygons1, List<Polygon> polygons2) {
    Node a = new Node(polygons1);
    Node b = new Node(polygons2);
    a.invert();
    b.clipTo(a);
    b.invert();
    a.clipTo(b);
    b.clipTo(a);
    a.build(b.allPolygons());
    a.invert();
    return a.allPolygons();
  }

  /**
   * Return a new CSG solid representing the intersection of this csg and the
   * specified csgs.
   *
   * <blockquote>
   *
   * <pre>
   *     A.intersect(B)
   *
   *     +-------+
   *     |       |
   *     |   A   |
   *     |    +--+----+   =   +--+
   *     +----+--+    |       +--+
   *          |   B   |
   *          |       |
   *          +-------+
   * }
   * </pre>
   *
   * </blockquote>
   *
   * @param csgs
   *          other csgs
   * @return intersection of this csg and the specified csgs
   */
  public CSG intersect(List<CSG> csgs) {

    if (csgs.isEmpty()) {
      return this.clone();
    }

    CSG csgsUnion = csgs.get(0);

    for (int i = 1; i < csgs.size(); i++) {
      csgsUnion = csgsUnion.union(csgs.get(i));
    }

    return intersect(csgsUnion);
  }

  /**
   * Return a new CSG solid representing the intersection of this csg and the
   * specified csgs.
   *
   * <blockquote>
   *
   * <pre>
   *     A.intersect(B)
   *
   *     +-------+
   *     |       |
   *     |   A   |
   *     |    +--+----+   =   +--+
   *     +----+--+    |       +--+
   *          |   B   |
   *          |       |
   *          +-------+
   * }
   * </pre>
   *
   * </blockquote>
   *
   * @param csgs
   *          other csgs
   * @return intersection of this csg and the specified csgs
   */
  public CSG intersect(CSG... csgs) {

    return intersect(Arrays.asList(csgs));
  }

  public CSG apply(Matrix4 matrix) {
    List<Polygon> newpolygons = polygons
        .stream()
        .map(p -> p.mul(matrix))
        .collect(Collectors.toList());
    return CSG.fromPolygons(newpolygons);
  }
}
