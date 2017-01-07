package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.x;
import static com.mikosik.jsolid.JSolid.y;
import static com.mikosik.jsolid.JSolid.z;
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

public class SolidImplTest {
  private List<Polygon> sides;
  private SolidImpl solid;

  @Test
  public void sides_returns_list_passed_to_constructor() throws Exception {
    given(sides = asList(new Polygon(x(), y(), z())));
    given(solid = new SolidImpl(sides));
    when(solid).sides();
    thenReturned(sides);
  }

  @Test
  public void sides_are_defensive_copied() throws Exception {
    given(sides = new ArrayList<>(asList(new Polygon(x(), y(), z()))));
    given(solid = new SolidImpl(sides));
    given(sides).add(new Polygon(z(), y(), x()));
    when(solid).sides();
    thenReturned(not(equalTo(sides)));
  }
}
