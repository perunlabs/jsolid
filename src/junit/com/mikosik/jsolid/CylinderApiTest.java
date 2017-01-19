package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.cylinder;
import static com.mikosik.jsolid.JSolid.funnel;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class CylinderApiTest {
  @Test
  public void negative_initial_segment_length_throws_exception() throws Exception {
    when(() -> cylinder(3, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_segment_length_throws_exception() throws Exception {
    when(() -> cylinder(3, 1).addSegment(3, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_segment_length_without_radius_throws_exception() throws Exception {
    when(() -> cylinder(3, 1).addSegment(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_initial_radius_throws_exception() throws Exception {
    when(() -> cylinder(-3, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_segment_radius_throws_exception() throws Exception {
    when(() -> cylinder(3, 1).addSegment(-3, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void zero_initial_radius_is_allowed() throws Exception {
    when(() -> cylinder(0, 1));
    thenReturned();
  }

  @Test
  public void zero_segment_radius_throws_exception() throws Exception {
    when(() -> cylinder(3, 1).addSegment(0, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_initial_funnel_length_throws_exception() throws Exception {
    when(() -> funnel(3, 2, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_funnel_length_throws_exception() throws Exception {
    when(() -> cylinder(3, 1).addFunnel(2, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_initial_funnel_radius_start_throws_exception() throws Exception {
    when(() -> funnel(-3, 2, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_initial_funnel_radius_end_throws_exception() throws Exception {
    when(() -> funnel(3, -2, 1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negative_funnel_radius_throws_exception() throws Exception {
    when(() -> cylinder(3, 1).addFunnel(-2, 1));
    thenThrown(IllegalArgumentException.class);
  }
}
