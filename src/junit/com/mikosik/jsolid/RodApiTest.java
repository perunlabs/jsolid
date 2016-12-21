package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.rod;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class RodApiTest {
  @Test
  public void negative_section_height_throws_exception() throws Exception {
    when(() -> rod().section(-1, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_section_radius_throws_exception() throws Exception {
    when(() -> rod().section(1, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_section_radius_throws_exception() throws Exception {
    when(() -> rod().section(1, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_funnel_height_throws_exception() throws Exception {
    when(() -> rod().funnel(-1, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_funnel_radius_throws_exception() throws Exception {
    when(() -> rod().funnel(1, -1));
    thenThrown(IllegalArgumentException.class);
  }
}
