package com.mikosik.jsolid.d2;

import static com.mikosik.jsolid.Solids.circle;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import com.mikosik.jsolid.d2.Circle;
import com.mikosik.jsolid.d2.Geometry;

public class CircleTest {
  private Circle circle;

  @Test
  public void circle_vertexes_are_convex_and_counter_clockwise() throws Exception {
    given(circle = circle(20));
    when(Geometry.isConvexCounterClockwisePolygon(circle.vertexes()));
    thenReturned(true);
  }
}
