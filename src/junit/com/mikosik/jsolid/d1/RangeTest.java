package com.mikosik.jsolid.d1;

import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class RangeTest {
  private Range range;

  @Test
  public void low_is_calculated_from_constructor_length_param() throws Exception {
    given(range = new Range(10));
    when(range.low);
    thenReturned(-5.0);
  }

  @Test
  public void high_is_calculated_from_constructor_length_param() throws Exception {
    given(range = new Range(10));
    when(range.high);
    thenReturned(5.0);
  }

  @Test
  public void low_returns_first_when_it_is_lower() throws Exception {
    given(range = new Range(3, 5));
    when(range.low);
    thenReturned(3.0);
  }

  @Test
  public void low_returns_second_when_it_is_lower() throws Exception {
    given(range = new Range(5, 3));
    when(range.low);
    thenReturned(3.0);
  }

  @Test
  public void high_returns_first_when_it_is_higher() throws Exception {
    given(range = new Range(5, 3));
    when(range.high);
    thenReturned(5.0);
  }

  @Test
  public void high_returns_second_when_it_is_higher() throws Exception {
    given(range = new Range(3, 5));
    when(range.high);
    thenReturned(5.0);
  }

  @Test
  public void length() throws Exception {
    given(range = new Range(3, 7));
    when(range.length());
    thenReturned(4.0);
  }

  @Test
  public void length_for_v2_lower_than_v1() throws Exception {
    given(range = new Range(7, 3));
    when(range.length());
    thenReturned(4.0);
  }
}
