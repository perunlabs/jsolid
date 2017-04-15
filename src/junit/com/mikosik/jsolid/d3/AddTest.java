package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.align;
import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.maxX;
import static com.mikosik.jsolid.JSolid.nothing;
import static com.mikosik.jsolid.util.ExceptionMatcher.exception;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class AddTest {
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
