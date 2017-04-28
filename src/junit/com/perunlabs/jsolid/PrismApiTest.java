package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.JSolid.circle;
import static com.perunlabs.jsolid.JSolid.prism;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class PrismApiTest {
  @Test
  public void null_base_argument_causes_exception() throws Exception {
    when(() -> prism(null, 1));
    thenThrown(NullPointerException.class);
  }

  @Test
  public void null_range_argument_causes_exception() throws Exception {
    when(() -> prism(circle(1), null));
    thenThrown(NullPointerException.class);
  }
}
