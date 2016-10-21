package com.mikosik.jsolid.d2;

import static com.mikosik.jsolid.Solids.v;
import static com.mikosik.jsolid.d2.Geometry.isConvexCounterClockwisePolygon;
import static java.util.Arrays.asList;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class TestGeometry {

  @Test
  public void is_convex_throws_exception_for_empty_vertex_list() throws Exception {
    when(() -> isConvexCounterClockwisePolygon(asList()));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void is_convex_throws_exception_for_single_vertex() throws Exception {
    when(() -> isConvexCounterClockwisePolygon(asList(v(1, 2))));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void is_convex_throws_exception_for_two_vertexes() throws Exception {
    when(() -> isConvexCounterClockwisePolygon(asList(v(1, 2), v(3, 4))));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void triangle_is_convex() throws Exception {
    when(() -> isConvexCounterClockwisePolygon(asList(v(0, 0), v(3, 0), v(0, 4))));
    thenReturned(true);
  }

  @Test
  public void triangle_with_clockwise_vertexes_is_not_convex() throws Exception {
    when(() -> isConvexCounterClockwisePolygon(asList(v(0, 0), v(0, 4), v(3, 0))));
    thenReturned(false);
  }

  @Test
  public void double_triangle_spiral_is_not_convex() throws Exception {
    when(() -> isConvexCounterClockwisePolygon(asList(
        v(0, 0), v(3, 0), v(0, 4), v(0, 0), v(3, 0), v(0, 4))));
    thenReturned(false);
  }
}
