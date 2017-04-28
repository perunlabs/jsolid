package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.JSolid.circle;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class CircleApiTest {
  @Test
  public void negative_radius_throws_exception() throws Exception {
    when(() -> circle(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_radius_throws_exception() throws Exception {
    when(() -> circle(-1));
    thenThrown(IllegalArgumentException.class);
  }
}
