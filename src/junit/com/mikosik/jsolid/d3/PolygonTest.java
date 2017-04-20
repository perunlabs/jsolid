package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.edge;
import static com.mikosik.jsolid.JSolid.v;
import static eu.mihosoft.vrl.v3d.Polygon.fromPoints;
import static java.util.Arrays.asList;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import eu.mihosoft.vrl.v3d.Polygon;

public class PolygonTest {
  private Polygon polygon;

  @Test
  public void edges() throws Exception {
    given(polygon = fromPoints(v(1, 1, 1), v(2, 2, 2), v(3, 3, 3)));
    when(() -> polygon.edges());
    thenReturned(asList(
        edge(v(1, 1, 1), v(2, 2, 2)),
        edge(v(2, 2, 2), v(3, 3, 3)),
        edge(v(3, 3, 3), v(1, 1, 1))));
  }
}
