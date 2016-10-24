package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.v;

import eu.mihosoft.vrl.v3d.Vector3d;

public class VectorUtils {
  public static Vector3d abs(Vector3d vector) {
    return v(Math.abs(vector.x), Math.abs(vector.y), Math.abs(vector.z));
  }
}
