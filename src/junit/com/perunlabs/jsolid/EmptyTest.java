package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.JSolid.cuboid;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import com.perunlabs.jsolid.d3.Cuboid;
import com.perunlabs.jsolid.d3.Solid;

public class EmptyTest {
  private Cuboid solid;

  @Test
  public void subtracting_empty_solid_does_not_change_anything() throws Exception {
    given(solid = cuboid(1, 2, 3));
    when(solid.sub(empty()).sides());
    thenReturned(solid.sides());
  }

  @Test
  public void subtracting_from_empty_solid_returns_empty_solid() throws Exception {
    when(empty().sub(solid).sides());
    thenReturned(empty().sides());
  }

  @Test
  public void adding_empty_solid_does_not_change_anything() throws Exception {
    given(solid = cuboid(1, 2, 3));
    when(solid.add(empty()).sides());
    thenReturned(solid.sides());
  }

  @Test
  public void adding_to_empty_solid_returns_added_solid() throws Exception {
    given(solid = cuboid(1, 2, 3));
    when(empty().add(solid).sides());
    thenReturned(solid.sides());
  }

  @Test
  public void intersecting_with_empty_solid_returns_empty_solid() throws Exception {
    when(cuboid(1, 1, 1).intersect(empty()).sides());
    thenReturned(empty().sides());
  }

  @Test
  public void intersecting_empty_solid_with_anything_returns_empty_solid() throws Exception {
    when(empty().intersect(cuboid(1, 1, 1)).sides());
    thenReturned(empty().sides());
  }

  private Solid empty() {
    return cuboid(1, 1, 1).sub(cuboid(9, 9, 9));
  }
}
