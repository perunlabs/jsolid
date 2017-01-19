package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.rod;
import static org.testory.Testory.given;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

import com.mikosik.jsolid.d3.Rod;

public class RodApiTest {
  private Rod rod;

  @Test
  public void negative_segment_length_throws_exception() throws Exception {
    when(() -> rod(3).addSegment(1, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_segment_length_without_radius_throws_exception() throws Exception {
    when(() -> rod(3).addSegment(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_segment_radius_throws_exception() throws Exception {
    when(() -> rod(3).addSegment(-1, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_segment_radius_throws_exception() throws Exception {
    when(() -> rod(3).addSegment(0, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_funnel_height_throws_exception() throws Exception {
    when(() -> rod(3).addSegment(2, 2).addFunnel(-1, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_funnel_radius_throws_exception() throws Exception {
    when(() -> rod(3).addSegment(2, 2).addFunnel(1, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void rod_without_segments_causes_exception() throws Exception {
    given(rod = rod(3));
    when(() -> rod.sides());
    thenThrown(IllegalStateException.class);
  }
}
