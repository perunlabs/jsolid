package com.perunlabs.jsolid.d2;

import java.util.List;

public class Geometry {
  public static boolean isConvexCounterClockwisePolygon(List<Vector2> vertexes) {
    if (vertexes.size() < 3) {
      throw new IllegalArgumentException(
          "At least 3 vertexes are needed. Received " + vertexes.size());
    }
    double lastAngle = vertexes.get(0).sub(vertexes.get(vertexes.size() - 1)).angle();
    double previous = lastAngle;
    boolean alreadyDecreased = false;
    for (int i = 0; i < vertexes.size() - 1; i++) {
      double angle = vertexes.get(i + 1).sub(vertexes.get(i)).angle();
      if (angle < previous) {
        if (alreadyDecreased) {
          return false;
        }
        alreadyDecreased = true;
      }
      previous = angle;
    }

    if (lastAngle < previous) {
      if (alreadyDecreased) {
        return false;
      }
    }
    return true;
  }
}
