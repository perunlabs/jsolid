package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.align;
import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.maxX;
import static com.mikosik.jsolid.JSolid.nothing;
import static com.mikosik.jsolid.JSolid.range;
import static com.mikosik.jsolid.JSolid.v;
import static com.mikosik.jsolid.util.SolidMatcher.matchesSolid;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class IntersectTest {
  private Cuboid solid;

  @Test
  public void intersect_with_itself_returns_itself() throws Exception {
    given(solid = cuboid(2, 2, 2));
    when(() -> solid.intersect(cuboid(2, 2, 2)));
    thenReturned(matchesSolid(
        v(-1, 1, 1),
        v(-1, 1, -1),
        v(-1, -1, 1),
        v(-1, -1, -1),
        v(1, 1, 1),
        v(1, 1, -1),
        v(1, -1, 1),
        v(1, -1, -1)));
  }

  @Test
  public void intersect_with_not_overlapping_returns_nothing() throws Exception {
    given(solid = cuboid(range(-1, 0), 2, 2));
    when(() -> solid.intersect(cuboid(range(0, 1), 2, 2)));
    thenReturned(matchesSolid());
  }

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
