package com.perunlabs.jsolid.d2;

import static com.perunlabs.jsolid.JSolid.range;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class RectangleTest {
  private Rectangle rectangle;

  @Test
  public void rectangle_vertexes_are_convex_and_counter_clockwise() throws Exception {
    given(rectangle = new Rectangle(range(10), range(20), 0));
    when(Geometry.isConvexCounterClockwisePolygon(rectangle.vertexes()));
    thenReturned(true);
  }

  @Test
  public void rounded_rectangle_vertexes_are_convex_and_counter_clockwise() throws Exception {
    given(rectangle = new Rectangle(range(10), range(20), 0).cornerR(100));
    when(Geometry.isConvexCounterClockwisePolygon(rectangle.vertexes()));
    thenReturned(true);
  }
}
