package com.mikosik.jsolid.d2;

import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import com.mikosik.jsolid.d2.Geometry;
import com.mikosik.jsolid.d2.Rectangle;

public class RectangleTest {
  private Rectangle rectangle;

  @Test
  public void rectangle_vertexes_are_convex_and_counter_clockwise() throws Exception {
    given(rectangle = new Rectangle().xd(10).yd(20));
    when(Geometry.isConvexCounterClockwisePolygon(rectangle.vertexes()));
    thenReturned(true);
  }

  @Test
  public void rounded_rectangle_vertexes_are_convex_and_counter_clockwise() throws Exception {
    given(rectangle = new Rectangle().xd(10).yd(20).cornerRMax());
    when(Geometry.isConvexCounterClockwisePolygon(rectangle.vertexes()));
    thenReturned(true);
  }
}
