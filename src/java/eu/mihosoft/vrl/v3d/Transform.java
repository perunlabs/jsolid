/**
 * Transform.java
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

import javax.vecmath.Matrix4d;

import com.mikosik.jsolid.d3.Vector3;

public class Transform {
  private final Matrix4d matrix;

  public Transform(double elements[]) {
    this.matrix = new Matrix4d(elements);
  }

  public Vector3 mul(Vector3 vec) {
    double x = matrix.m00 * vec.x + matrix.m01 * vec.y + matrix.m02 * vec.z + matrix.m03;
    double y = matrix.m10 * vec.x + matrix.m11 * vec.y + matrix.m12 * vec.z + matrix.m13;
    double z = matrix.m20 * vec.x + matrix.m21 * vec.y + matrix.m22 * vec.z + matrix.m23;
    return v(x, y, z);
  }

  public Transform mul(Transform t) {
    matrix.mul(t.matrix);
    return this;
  }

  public boolean isMirror() {
    return matrix.determinant() < 0;
  }

  public String toString() {
    return matrix.toString();
  }

  public static Transform unity() {
    return new Transform(new double[] {
        1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1
    });
  }

  public static Transform rotateX(double degrees) {
    double radians = degrees * Math.PI * (1.0 / 180.0);
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    double elemenents[] = {
        1, 0, 0, 0, 0, cos, sin, 0, 0, -sin, cos, 0, 0, 0, 0, 1
    };
    return new Transform(elemenents);
  }

  public static Transform rotateY(double degrees) {
    double radians = degrees * Math.PI * (1.0 / 180.0);
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    double elemenents[] = {
        cos, 0, -sin, 0, 0, 1, 0, 0, sin, 0, cos, 0, 0, 0, 0, 1
    };
    return new Transform(elemenents);
  }

  public static Transform rotateZ(double degrees) {
    double radians = degrees * Math.PI * (1.0 / 180.0);
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    double elemenents[] = {
        cos, sin, 0, 0, -sin, cos, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1
    };
    return new Transform(elemenents);
  }

  public static Transform rotate(Vector3 vec) {
    rotateX(vec.x);
    Transform.rotateY(vec.y);
    return Transform.rotateZ(vec.z);
  }

  public static Transform translate(Vector3 vec) {
    double elemenents[] = {
        1, 0, 0, vec.x,
        0, 1, 0, vec.y,
        0, 0, 1, vec.z,
        0, 0, 0, 1
    };
    return new Transform(elemenents);
  }

  public static Transform mirror(Plane plane) {
    System.err.println(
        "WARNING: I'm too dumb to implement the mirror() operation correctly. Please fix me!");

    double nx = plane.normal.x;
    double ny = plane.normal.y;
    double nz = plane.normal.z;
    double w = plane.dist;
    double elemenents[] = {
        (1.0 - 2.0 * nx * nx), (-2.0 * ny * nx), (-2.0 * nz * nx), 0,
        (-2.0 * nx * ny), (1.0 - 2.0 * ny * ny), (-2.0 * nz * ny), 0,
        (-2.0 * nx * nz), (-2.0 * ny * nz), (1.0 - 2.0 * nz * nz), 0,
        (-2.0 * nx * w), (-2.0 * ny * w), (-2.0 * nz * w), 1
    };
    return new Transform(elemenents);
  }

  public static Transform scale(Vector3 vec) {
    if (vec.x == 0 || vec.y == 0 || vec.z == 0) {
      throw new IllegalArgumentException("scale by 0 not allowed!");
    }
    double elemenents[] = { vec.x, 0, 0, 0, 0, vec.y, 0, 0, 0, 0, vec.z, 0, 0, 0, 0, 1 };
    return new Transform(elemenents);
  }
}
