package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.align;
import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.maxX;
import static com.mikosik.jsolid.JSolid.nothing;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class IntersectTest {
  @Test
  public void intersecting_with_aligned_nothing_succeeds() throws Exception {
    when(() -> cuboid(1, 2, 3).intersect(nothing(), maxX()).sides());
    thenReturned();
  }

  @Test
  public void intersecting_nothing_with_aligned_succeeds() throws Exception {
    when(() -> nothing().intersect(cuboid(1, 2, 3), maxX()).sides());
    thenReturned();
  }

  @Test
  public void intersecting_with_anchored_nothing_succeeds() throws Exception {
    when(() -> cuboid(1, 2, 3).intersect(nothing(), align(maxX())).sides());
    thenReturned();
  }

  @Test
  public void intersecting_nothing_with_anchored_succeeds() throws Exception {
    when(() -> nothing().intersect(cuboid(1, 2, 3), align(maxX())).sides());
    thenReturned();
  }
}
