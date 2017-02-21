package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.d1.Anchor1.CENTER;
import static com.mikosik.jsolid.d1.Anchor1.MAX;
import static com.mikosik.jsolid.d1.Anchor1.MIN;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class Anchor1Test {
  private Range range;

  @Test
  public void move_to_center() throws Exception {
    given(range = new Range(3, 5));
    when(CENTER.moveTo(range, 7));
    thenReturned(new Range(6, 8));
  }

  @Test
  public void move_to_min() throws Exception {
    given(range = new Range(3, 5));
    when(MIN.moveTo(range, 7));
    thenReturned(new Range(7, 9));
  }

  @Test
  public void move_to_max() throws Exception {
    given(range = new Range(3, 5));
    when(MAX.moveTo(range, 7));
    thenReturned(new Range(5, 7));
  }
}
