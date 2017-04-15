package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.align;
import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.maxX;
import static com.mikosik.jsolid.JSolid.nothing;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class SubTest {
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
