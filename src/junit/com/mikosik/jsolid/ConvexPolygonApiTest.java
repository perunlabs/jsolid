package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.convexPolygon;
import static com.mikosik.jsolid.JSolid.v;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class ConvexPolygonApiTest {
  @Test
  public void zero_vertexes_throws_exception() throws Exception {
    when(() -> convexPolygon());
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void one_vertex_throws_exception() throws Exception {
    when(() -> convexPolygon(v(1, 1)));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void two_vertexes_throws_exception() throws Exception {
    when(() -> convexPolygon(v(1, 1), v(2, 2)));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void null_argument_causes_exception() throws Exception {
    when(() -> convexPolygon(v(1, 1), v(2, 2), null));
    thenThrown(NullPointerException.class);
  }
}
