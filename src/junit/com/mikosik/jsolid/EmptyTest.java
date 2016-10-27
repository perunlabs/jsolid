package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.box;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import com.mikosik.jsolid.d3.Box;
import com.mikosik.jsolid.d3.Solid;

public class EmptyTest {
  private Box solid;

  @Test
  public void subtracting_empty_solid_does_not_change_anything() throws Exception {
    given(solid = box(1, 2, 3));
    when(solid.minus(empty()).toStl());
    thenReturned(solid.toStl());
  }

  @Test
  public void subtracting_from_empty_solid_returns_empty_solid() throws Exception {
    when(empty().minus(solid).toStl());
    thenReturned(empty().toStl());
  }

  @Test
  public void adding_empty_solid_does_not_change_anything() throws Exception {
    given(solid = box(1, 2, 3));
    when(solid.plus(empty()).toStl());
    thenReturned(solid.toStl());
  }

  @Test
  public void adding_to_empty_solid_returns_added_solid() throws Exception {
    given(solid = box(1, 2, 3));
    when(empty().plus(solid).toStl());
    thenReturned(solid.toStl());
  }

  @Test
  public void intersecting_with_empty_solid_returns_empty_solid() throws Exception {
    when(box(1, 1, 1).intersect(empty()).toStl());
    thenReturned(empty().toStl());
  }

  @Test
  public void intersecting_empty_solid_with_anything_returns_empty_solid() throws Exception {
    when(empty().intersect(box(1, 1, 1)).toStl());
    thenReturned(empty().toStl());
  }

  private Solid empty() {
    return box(1, 1, 1).minus(box(9, 9, 9));
  }
}
