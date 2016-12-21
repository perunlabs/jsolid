package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.box;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class BoxApiTest {
  @Test
  public void negative_z_corner_throws_exception() throws Exception {
    when(() -> box(1, 2, 3).zCornerR(-1));
    thenThrown(IllegalArgumentException.class);
  }
}
