package com.perunlabs.jsolid.d3.op;

import static com.perunlabs.jsolid.JSolid.x;
import static com.perunlabs.jsolid.JSolid.y;
import static com.perunlabs.jsolid.JSolid.z;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import eu.mihosoft.vrl.v3d.Polygon;

public class PolygonsSolidTest {
  private List<Polygon> sides;
  private PolygonsSolid solid;

  @Test
  public void sides_returns_list_passed_to_constructor() throws Exception {
    given(sides = asList(new Polygon(x(), y(), z())));
    given(solid = new PolygonsSolid(sides));
    when(solid).sides();
    thenReturned(sides);
  }

  @Test
  public void sides_are_defensive_copied() throws Exception {
    given(sides = new ArrayList<>(asList(new Polygon(x(), y(), z()))));
    given(solid = new PolygonsSolid(sides));
    given(sides).add(new Polygon(z(), y(), x()));
    when(solid).sides();
    thenReturned(not(equalTo(sides)));
  }
}
