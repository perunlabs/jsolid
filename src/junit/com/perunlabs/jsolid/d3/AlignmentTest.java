package com.perunlabs.jsolid.d3;

import static com.perunlabs.jsolid.JSolid.align;
import static com.perunlabs.jsolid.JSolid.centerX;
import static com.perunlabs.jsolid.JSolid.cuboid;
import static com.perunlabs.jsolid.JSolid.maxX;
import static com.perunlabs.jsolid.JSolid.minX;
import static com.perunlabs.jsolid.JSolid.vx;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class AlignmentTest {
  private Alignment alignment;

  @Test
  public void alignment() throws Exception {
    given(alignment = new Alignment(maxX(), minX(), 0));
    when(alignment.alignShiftFor(cuboid(3, 7, 11), cuboid(5, 13, 17)));
    thenReturned(vx(4));
  }

  @Test
  public void alignment_with_shift() throws Exception {
    given(alignment = new Alignment(maxX(), minX(), 3));
    when(alignment.alignShiftFor(cuboid(3, 7, 11), cuboid(5, 13, 17)));
    thenReturned(vx(7));
  }

  @Test
  public void aligning_with_margin_using_two_not_edgy_anchors_fails() throws Exception {
    when(() -> align(centerX(), 3, centerX()));
    thenThrown(IllegalArgumentException.class);
  }
}
