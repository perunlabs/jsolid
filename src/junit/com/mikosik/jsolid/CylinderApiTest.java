package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.cylinder;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class CylinderApiTest {
  @Test
  public void negative_radius_throws_exception() throws Exception {
    when(() -> cylinder(-1, 10));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_radius_throws_exception() throws Exception {
    when(() -> cylinder(0, 10));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_length_throws_exception() throws Exception {
    when(() -> cylinder(1, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_length_throws_exception() throws Exception {
    when(() -> cylinder(1, 0));
    thenThrown(IllegalArgumentException.class);
  }

}
