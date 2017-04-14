package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.range;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import com.mikosik.jsolid.JSolid;

public class Anchor3Test {
  private Cuboid cuboid;

  @Test
  public void minX() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.minX().valueIn(cuboid));
    thenReturned(1.0);
  }

  @Test
  public void centerX() {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.centerX().valueIn(cuboid));
    thenReturned(1.5);
  }

  @Test
  public void maxX() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.maxX().valueIn(cuboid));
    thenReturned(2.0);
  }

  @Test
  public void minY() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.minY().valueIn(cuboid));
    thenReturned(3.0);
  }

  @Test
  public void centerY() {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.centerY().valueIn(cuboid));
    thenReturned(3.5);
  }

  @Test
  public void maxY() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.maxY().valueIn(cuboid));
    thenReturned(4.0);
  }

  @Test
  public void minZ() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.minZ().valueIn(cuboid));
    thenReturned(5.0);
  }

  @Test
  public void centerZ() {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.centerZ().valueIn(cuboid));
    thenReturned(5.5);
  }

  @Test
  public void maxZ() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(JSolid.maxZ().valueIn(cuboid));
    thenReturned(6.0);
  }
}
