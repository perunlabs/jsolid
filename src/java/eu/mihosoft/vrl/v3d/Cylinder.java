/**
 * Cylinder.java
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

import static com.mikosik.jsolid.JSolid.v;
import static com.mikosik.jsolid.JSolid.v0;
import static com.mikosik.jsolid.JSolid.z;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mikosik.jsolid.d3.Vector3;

/**
 * A solid cylinder.
 *
 * The tessellation can be controlled via the {@link #numSlices} parameter.
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class Cylinder implements Primitive {

  private Vector3 start;
  private Vector3 end;
  private double startRadius;
  private double endRadius;
  private int numSlices;

  /**
   * Constructor. Creates a new cylinder with center {@code [0,0,0]} and ranging
   * from {@code [0,-0.5,0]} to {@code [0,0.5,0]}, i.e. {@code size = 1}.
   */
  public Cylinder() {
    this.start = v(0, -0.5, 0);
    this.end = v(0, 0.5, 0);
    this.startRadius = 1;
    this.endRadius = 1;
    this.numSlices = 16;
  }

  /**
   * Constructor. Creates a cylinder ranging from {@code start} to {@code end}
   * with the specified {@code radius}. The resolution of the tessellation can
   * be controlled with {@code numSlices}.
   *
   * @param start
   *          cylinder start
   * @param end
   *          cylinder end
   * @param radius
   *          cylinder radius
   * @param numSlices
   *          number of slices (used for tessellation)
   */
  public Cylinder(Vector3 start, Vector3 end, double radius, int numSlices) {
    this.start = start;
    this.end = end;
    this.startRadius = radius;
    this.endRadius = radius;
    this.numSlices = numSlices;
  }

  /**
   * Constructor. Creates a cylinder ranging from {@code start} to {@code end}
   * with the specified {@code radius}. The resolution of the tessellation can
   * be controlled with {@code numSlices}.
   *
   * @param start
   *          cylinder start
   * @param end
   *          cylinder end
   * @param startRadius
   *          cylinder start radius
   * @param endRadius
   *          cylinder end radius
   * @param numSlices
   *          number of slices (used for tessellation)
   */
  public Cylinder(Vector3 start, Vector3 end, double startRadius, double endRadius,
      int numSlices) {
    this.start = start;
    this.end = end;
    this.startRadius = startRadius;
    this.endRadius = endRadius;
    this.numSlices = numSlices;
  }

  /**
   * Constructor. Creates a cylinder ranging from {@code [0,0,0]} to
   * {@code [0,0,height]} with the specified {@code radius} and {@code height}.
   * The resolution of the tessellation can be controlled with
   * {@code numSlices}.
   *
   * @param radius
   *          cylinder radius
   * @param height
   *          cylinder height
   * @param numSlices
   *          number of slices (used for tessellation)
   */
  public Cylinder(double radius, double height, int numSlices) {
    this.start = v0();
    this.end = z().mul(height);
    this.startRadius = radius;
    this.endRadius = radius;
    this.numSlices = numSlices;
  }

  /**
   * Constructor. Creates a cylinder ranging from {@code [0,0,0]} to
   * {@code [0,0,height]} with the specified {@code radius} and {@code height}.
   * The resolution of the tessellation can be controlled with
   * {@code numSlices}.
   *
   * @param startRadius
   *          cylinder start radius
   * @param endRadius
   *          cylinder end radius
   * @param height
   *          cylinder height
   * @param numSlices
   *          number of slices (used for tessellation)
   */
  public Cylinder(double startRadius, double endRadius, double height, int numSlices) {
    this.start = v0();
    this.end = z().mul(height);
    this.startRadius = startRadius;
    this.endRadius = endRadius;
    this.numSlices = numSlices;
  }

  @Override
  public List<Polygon> toPolygons() {
    final Vector3 s = getStart();
    Vector3 e = getEnd();
    final Vector3 ray = e.minus(s);
    final Vector3 axisZ = ray.normalize();
    boolean isY = (Math.abs(axisZ.y) > 0.5);
    final Vector3 axisX = v(isY ? 1 : 0, !isY ? 1 : 0, 0).cross(axisZ).normalize();
    final Vector3 axisY = axisX.cross(axisZ).normalize();
    Vector3 startV = s;
    Vector3 endV = e;
    List<Polygon> polygons = new ArrayList<>();

    for (int i = 0; i < numSlices; i++) {
      double t0 = i / (double) numSlices, t1 = (i + 1) / (double) numSlices;
      polygons.add(new Polygon(Arrays.asList(
          startV,
          cylPoint(axisX, axisY, axisZ, ray, s, startRadius, 0, t0, -1),
          cylPoint(axisX, axisY, axisZ, ray, s, startRadius, 0, t1, -1))));
      polygons.add(new Polygon(Arrays.asList(
          cylPoint(axisX, axisY, axisZ, ray, s, startRadius, 0, t1, 0),
          cylPoint(axisX, axisY, axisZ, ray, s, startRadius, 0, t0, 0),
          cylPoint(axisX, axisY, axisZ, ray, s, endRadius, 1, t0, 0),
          cylPoint(axisX, axisY, axisZ, ray, s, endRadius, 1, t1, 0))));
      polygons.add(new Polygon(
          Arrays.asList(
              endV,
              cylPoint(axisX, axisY, axisZ, ray, s, endRadius, 1, t1, 1),
              cylPoint(axisX, axisY, axisZ, ray, s, endRadius, 1, t0, 1))));
    }

    return polygons;
  }

  private Vector3 cylPoint(
      Vector3 axisX, Vector3 axisY, Vector3 axisZ, Vector3 ray, Vector3 s,
      double r, double stack, double slice, double normalBlend) {
    double angle = slice * Math.PI * 2;
    Vector3 out = axisX.mul(Math.cos(angle)).plus(axisY.mul(Math.sin(angle)));
    Vector3 pos = s.plus(ray.mul(stack)).plus(out.mul(r));
    return pos;
  }

  /**
   * @return the start
   */
  public Vector3 getStart() {
    return start;
  }

  /**
   * @param start
   *          the start to set
   */
  public void setStart(Vector3 start) {
    this.start = start;
  }

  /**
   * @return the end
   */
  public Vector3 getEnd() {
    return end;
  }

  /**
   * @param end
   *          the end to set
   */
  public void setEnd(Vector3 end) {
    this.end = end;
  }

  /**
   * @return the radius
   */
  public double getStartRadius() {
    return startRadius;
  }

  /**
   * @param radius
   *          the radius to set
   */
  public void setStartRadius(double radius) {
    this.startRadius = radius;
  }

  /**
   * @return the radius
   */
  public double getEndRadius() {
    return endRadius;
  }

  /**
   * @param radius
   *          the radius to set
   */
  public void setEndRadius(double radius) {
    this.endRadius = radius;
  }

  /**
   * @return the number of slices
   */
  public int getNumSlices() {
    return numSlices;
  }

  /**
   * @param numSlices
   *          the number of slices to set
   */
  public void setNumSlices(int numSlices) {
    this.numSlices = numSlices;
  }
}
