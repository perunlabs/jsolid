package com.perunlabs.jsolid.d3.op;

import static com.perunlabs.jsolid.JSolid.align;
import static com.perunlabs.jsolid.JSolid.cuboid;
import static com.perunlabs.jsolid.JSolid.maxX;
import static com.perunlabs.jsolid.JSolid.nothing;
import static com.perunlabs.jsolid.JSolid.range;
import static com.perunlabs.jsolid.JSolid.v;
import static com.perunlabs.jsolid.util.SolidMatcher.matchesSolid;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import com.perunlabs.jsolid.d3.Cuboid;

public class SubTest {
  private Cuboid solid;

  @Test
  public void sub_half_of_cuboid() throws Exception {
    given(solid = cuboid(2, 2, 2));
    when(() -> solid.sub(cuboid(range(0, 1), 2, 2)));
    thenReturned(matchesSolid(
        v(0, 1, 1),
        v(0, 1, -1),
        v(0, -1, 1),
        v(0, -1, -1),
        v(-1, 1, 1),
        v(-1, 1, -1),
        v(-1, -1, 1),
        v(-1, -1, -1)));
  }

  @Test
  public void sub_itself_returns_nothing() throws Exception {
    given(solid = cuboid(2, 2, 2));
    when(() -> solid.sub(solid));
    thenReturned(matchesSolid());
  }

  @Test
  public void sub_enclosing_solid_returns_nothing() throws Exception {
    given(solid = cuboid(2, 2, 2));
    when(() -> solid.sub(cuboid(4, 4, 4)));
    thenReturned(matchesSolid());
  }

  @Test
  public void subtracting_aligned_nothing_succeeds() throws Exception {
    when(() -> cuboid(1, 2, 3).sub(nothing(), maxX()).sides());
    thenReturned();
  }

  @Test
  public void subtracting_aligned_from_nothing_succeeds() throws Exception {
    when(() -> nothing().sub(cuboid(1, 2, 3), maxX()).sides());
    thenReturned();
  }

  @Test
  public void subtracting_anchored_nothing_succeeds() throws Exception {
    when(() -> cuboid(1, 2, 3).sub(nothing(), align(maxX())).sides());
    thenReturned();
  }

  @Test
  public void subtracting_anchored_from_nothing_succeeds() throws Exception {
    when(() -> nothing().sub(cuboid(1, 2, 3), align(maxX())).sides());
    thenReturned();
  }
}
