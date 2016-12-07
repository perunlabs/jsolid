package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.box;
import static com.mikosik.jsolid.JSolid.vx;
import static com.mikosik.jsolid.JSolid.x;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class AlignmentTest {
  private Alignment<?> alignment;

  @Test
  public void alignment() throws Exception {
    given(alignment = new Alignment<>(x().max(), x().min()));
    when(alignment.alignShiftFor(box(3, 7, 11), box(5, 13, 17)));
    thenReturned(vx(4));
  }
}
