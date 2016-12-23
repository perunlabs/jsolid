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

import javax.vecmath.Matrix4d;

public class Transform {
  private final Matrix4d m;

  public Transform() {
    m = new Matrix4d();
    m.m00 = 1;
    m.m11 = 1;
    m.m22 = 1;
    m.m33 = 1;
  }

  public static Transform unity() {
    return new Transform();
  }

  public Transform rotX(double degrees) {
    double radians = degrees * Math.PI * (1.0 / 180.0);
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    double elemenents[] = {
        1, 0, 0, 0, 0, cos, sin, 0, 0, -sin, cos, 0, 0, 0, 0, 1
    };
    m.mul(new Matrix4d(elemenents));
    return this;
  }

  public Transform rotY(double degrees) {
    double radians = degrees * Math.PI * (1.0 / 180.0);
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    double elemenents[] = {
        cos, 0, -sin, 0, 0, 1, 0, 0, sin, 0, cos, 0, 0, 0, 0, 1
    };
    m.mul(new Matrix4d(elemenents));
    return this;
  }

  public Transform rotZ(double degrees) {
    double radians = degrees * Math.PI * (1.0 / 180.0);
    double cos = Math.cos(radians);
    double sin = Math.sin(radians);
    double elemenents[] = {
        cos, sin, 0, 0, -sin, cos, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1
    };
    m.mul(new Matrix4d(elemenents));
    return this;
  }

  public Transform rot(Vector3d vec) {
    return rotX(vec.x).rotY(vec.y).rotZ(vec.z);
  }

  public Transform translate(Vector3d vec) {
    double elemenents[] = {
        1, 0, 0, vec.x,
        0, 1, 0, vec.y,
        0, 0, 1, vec.z,
        0, 0, 0, 1
    };
    m.mul(new Matrix4d(elemenents));
    return this;
  }

  public Transform mirror(Plane plane) {
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
    m.mul(new Matrix4d(elemenents));
    return this;
  }

  public Transform scale(Vector3d vec) {
    if (vec.x == 0 || vec.y == 0 || vec.z == 0) {
      throw new IllegalArgumentException("scale by 0 not allowed!");
    }
    double elemenents[] = { vec.x, 0, 0, 0, 0, vec.y, 0, 0, 0, 0, vec.z, 0, 0, 0, 0, 1 };
    m.mul(new Matrix4d(elemenents));
    return this;
  }

  public Vector3d transform(Vector3d vec) {
    double x = m.m00 * vec.x + m.m01 * vec.y + m.m02 * vec.z + m.m03;
    double y = m.m10 * vec.x + m.m11 * vec.y + m.m12 * vec.z + m.m13;
    double z = m.m20 * vec.x + m.m21 * vec.y + m.m22 * vec.z + m.m23;
    return new Vector3d(x, y, z);
  }

  public boolean isMirror() {
    return m.determinant() < 0;
  }

  public Transform apply(Transform t) {
    m.mul(t.m);
    return this;
  }

  public String toString() {
    return m.toString();
  }
}
