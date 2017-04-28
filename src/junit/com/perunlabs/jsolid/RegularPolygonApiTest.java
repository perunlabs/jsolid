package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.JSolid.regularPolygon;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class RegularPolygonApiTest {
  @Test
  public void negative_radius_throws_exception() throws Exception {
    when(() -> regularPolygon(-1, 10));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_radius_throws_exception() throws Exception {
    when(() -> regularPolygon(0, 10));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_vertex_count_throws_exception() throws Exception {
    when(() -> regularPolygon(10, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_vertex_count_throws_exception() throws Exception {
    when(() -> regularPolygon(10, 0));
    thenThrown(IllegalArgumentException.class);
  }
}
