package com.perunlabs.jsolid.d2;

import static com.perunlabs.jsolid.JSolid.circle;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class CircleTest {
  private Circle circle;

  @Test
  public void circle_vertexes_are_convex_and_counter_clockwise() throws Exception {
    given(circle = circle(20));
    when(Geometry.isConvexCounterClockwisePolygon(circle.vertexes()));
    thenReturned(true);
  }
}
