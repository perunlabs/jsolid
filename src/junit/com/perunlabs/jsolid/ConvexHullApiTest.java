package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.JSolid.convexHull;
import static com.perunlabs.jsolid.JSolid.v;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class ConvexHullApiTest {
  @Test
  public void zero_arguments_throws_exception() throws Exception {
    when(() -> convexHull());
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void one_argument_throws_exception() throws Exception {
    when(() -> convexHull(v(1, 1, 1)));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void two_arguments_throws_exception() throws Exception {
    when(() -> convexHull(v(1, 1, 1), v(2, 2, 2)));
    thenThrown(IllegalArgumentException.class);
  }
}
