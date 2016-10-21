package com.mikosik.jsolid.d1;

import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class TestRange {
  private Range range;

  @Test
  public void v1_set_by_vd() throws Exception {
    given(range = new Range());
    when(range.vd(10).v1);
    thenReturned(-5.0);
  }

  @Test
  public void v2_set_by_vd() throws Exception {
    given(range = new Range());
    when(range.vd(10).v2);
    thenReturned(5.0);
  }

  @Test
  public void low_returns_first_when_it_is_lower() throws Exception {
    given(range = new Range().v(3, 5));
    when(range.high());
    thenReturned(5.0);
  }

  @Test
  public void low_returns_second_when_it_is_lower() throws Exception {
    given(range = new Range().v(5, 3));
    when(range.high());
    thenReturned(5.0);
  }

  @Test
  public void high_returns_first_when_it_is_higher() throws Exception {
    given(range = new Range().v(5, 3));
    when(range.high());
    thenReturned(5.0);
  }

  @Test
  public void high_returns_second_when_it_is_higher() throws Exception {
    given(range = new Range().v(3, 5));
    when(range.high());
    thenReturned(5.0);
  }

  @Test
  public void size() throws Exception {
    given(range = new Range().v(3, 7));
    when(range.length());
    thenReturned(4.0);
  }

  @Test
  public void size_for_v2_lower_than_v1() throws Exception {
    given(range = new Range().v(7, 3));
    when(range.length());
    thenReturned(4.0);
  }
}
