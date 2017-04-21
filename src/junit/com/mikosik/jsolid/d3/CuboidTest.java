package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.range;
import static com.mikosik.jsolid.JSolid.v;
import static com.mikosik.jsolid.util.SolidMatcher.matchesSolid;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class CuboidTest {
  @Test
  public void simple_cube() throws Exception {
    when(cuboid(2, 2, 2));
    thenReturned(matchesSolid(
        v(1, 1, 1),
        v(1, 1, -1),
        v(1, -1, 1),
        v(1, -1, -1),
        v(-1, 1, 1),
        v(-1, 1, -1),
        v(-1, -1, 1),
        v(-1, -1, -1)));
  }

  @Test
  public void range_x() throws Exception {
    when(cuboid(range(-7, 3), 2, 2));
    thenReturned(matchesSolid(
        v(-7, 1, 1),
        v(-7, 1, -1),
        v(-7, -1, 1),
        v(-7, -1, -1),
        v(3, 1, 1),
        v(3, 1, -1),
        v(3, -1, 1),
        v(3, -1, -1)));
  }

  @Test
  public void range_y() throws Exception {
    when(cuboid(2, range(-7, 3), 2));
    thenReturned(matchesSolid(
        v(1, 3, 1),
        v(1, 3, -1),
        v(1, -7, 1),
        v(1, -7, -1),
        v(-1, 3, 1),
        v(-1, 3, -1),
        v(-1, -7, 1),
        v(-1, -7, -1)));
  }

  @Test
  public void range_z() throws Exception {
    when(cuboid(2, 2, range(-7, 3)));
    thenReturned(matchesSolid(
        v(1, 1, 3),
        v(1, 1, -7),
        v(1, -1, 3),
        v(1, -1, -7),
        v(-1, 1, 3),
        v(-1, 1, -7),
        v(-1, -1, 3),
        v(-1, -1, -7)));
  }
}
