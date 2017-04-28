package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.JSolid.rectangle;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class RectangleApiTest {
  @Test
  public void negative_corner_throws_exception() throws Exception {
    when(() -> rectangle(1, 2).cornerR(-1));
    thenThrown(IllegalArgumentException.class);
  }
}
