package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.align;
import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.maxX;
import static com.mikosik.jsolid.JSolid.nothing;
import static com.mikosik.jsolid.JSolid.range;
import static com.mikosik.jsolid.JSolid.v;
import static com.mikosik.jsolid.util.ExceptionMatcher.exception;
import static com.mikosik.jsolid.util.SolidMatcher.matchesSolid;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class AddTest {
  private Cuboid solid;

  @Test
  public void add_two_cuboid_halves() throws Exception {
    given(solid = cuboid(range(-1, 0), 2, 2));
    when(() -> solid.add(cuboid(range(0, 1), 2, 2)));
    thenReturned(matchesSolid(
        v(-1, 1, 1),
        v(-1, 1, -1),
        v(-1, -1, 1),
        v(-1, -1, -1),
        v(0, 1, 1),
        v(0, 1, -1),
        v(0, -1, 1),
        v(0, -1, -1),
        v(1, 1, 1),
        v(1, 1, -1),
        v(1, -1, 1),
        v(1, -1, -1)));
  }

  @Test
  public void add_itself_returns_itself() throws Exception {
    given(solid = cuboid(2, 2, 2));
    when(() -> solid.add(cuboid(2, 2, 2)));
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
  public void adding_aligned_nothing_succeeds() throws Exception {
    when(() -> cuboid(1, 2, 3).add(nothing(), align(maxX())).sides());
    thenReturned();
  }

  @Test
  public void adding_aligned_to_nothing_fails() throws Exception {
    when(() -> nothing().add(cuboid(1, 2, 3), align(maxX())).sides());
    thenThrown(exception(new IllegalArgumentException(
        "It is not possible to align against empty Solid.")));
  }

  @Test
  public void adding_anchored_nothing_succeeds() throws Exception {
    when(() -> cuboid(1, 2, 3).add(nothing(), maxX()).sides());
    thenReturned();
  }

  @Test
  public void adding_anchored_to_nothing_fails() throws Exception {
    when(() -> nothing().add(cuboid(1, 2, 3), maxX()).sides());
    thenThrown(exception(new IllegalArgumentException(
        "It is not possible to align against empty Solid.")));
  }
}
