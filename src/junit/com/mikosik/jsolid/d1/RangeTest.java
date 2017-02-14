package com.mikosik.jsolid.d1;

import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class RangeTest {
  private Range range;

  @Test
  public void low_is_calculated_from_constructor_size_param() throws Exception {
    given(range = new Range(10));
    when(range.low);
    thenReturned(-5.0);
  }

  @Test
  public void high_is_calculated_from_constructor_size_param() throws Exception {
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
  public void size() throws Exception {
    given(range = new Range(3, 7));
    when(range.size());
    thenReturned(4.0);
  }

  @Test
  public void size_for_v2_lower_than_v1() throws Exception {
    given(range = new Range(7, 3));
    when(range.size());
    thenReturned(4.0);
  }

  @Test
  public void center() throws Exception {
    given(range = new Range(3, 7));
    when(range.center());
    thenReturned(5.0);
  }

  @Test
  public void center_for_v2_lower_than_v1() throws Exception {
    given(range = new Range(7, 3));
    when(range.center());
    thenReturned(5.0);
  }

  @Test
  public void resize_to() throws Exception {
    given(range = new Range(3, 5));
    when(range.resizeTo(10));
    thenReturned(new Range(-1, 9));
  }

  @Test
  public void resize_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(range.resizeTo(0));
    thenReturned(new Range(4, 4));
  }

  @Test
  public void resize_to_fails_for_negative_value() throws Exception {
    given(range = new Range(3, 5));
    when(() -> range.resizeTo(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by() throws Exception {
    given(range = new Range(3, 5));
    when(range.resizeBy(2));
    thenReturned(new Range(2, 6));
  }

  @Test
  public void resize_by_zero() throws Exception {
    given(range = new Range(3, 5));
    when(range.resizeBy(0));
    thenReturned(new Range(3, 5));
  }

  @Test
  public void resize_by_negative_value() throws Exception {
    given(range = new Range(3, 5));
    when(range.resizeBy(-1));
    thenReturned(new Range(3.5, 4.5));
  }

  @Test
  public void resize_by_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(range.resizeBy(-2));
    thenReturned(new Range(4, 4));
  }

  @Test
  public void resize_by_below_zero_fails() throws Exception {
    given(range = new Range(3, 5));
    when(() -> range.resizeBy(-3));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void add() throws Exception {
    given(range = new Range(3, 5));
    when(range.add(7));
    thenReturned(new Range(10, 12));
  }

  @Test
  public void mul() throws Exception {
    given(range = new Range(3, 5));
    when(range.mul(2));
    thenReturned(new Range(2, 6));
  }

  @Test
  public void mul_by_negative_not_allowed() throws Exception {
    given(range = new Range(3, 5));
    when(() -> range.mul(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void grow() throws Exception {
    given(range = new Range(3, 5));
    when(range.grow(2));
    thenReturned(new Range(1, 7));
  }

  @Test
  public void negative_grow_shrinks_range() throws Exception {
    given(range = new Range(3, 13));
    when(range.grow(-2));
    thenReturned(new Range(5, 11));
  }

  @Test
  public void negative_grow_greater_equal_to_half_of_size_shrinks_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(range.grow(-1));
    thenReturned(new Range(4, 4));
  }

  @Test
  public void negative_grow_greater_than_half_of_size_shrinks_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(range.grow(-3));
    thenReturned(new Range(4, 4));
  }

  @Test
  public void equal_ranges() throws Exception {
    given(range = new Range(3, 5));
    when(range.equals(new Range(3, 5)));
    thenReturned(true);
  }

  @Test
  public void not_equal_low_value() throws Exception {
    given(range = new Range(3, 5));
    when(range.equals(new Range(4, 5)));
    thenReturned(false);
  }

  @Test
  public void not_equal_high_value() throws Exception {
    given(range = new Range(3, 5));
    when(range.equals(new Range(3, 6)));
    thenReturned(false);
  }

  @Test
  public void to_string() throws Exception {
    given(range = new Range(3, 5));
    when(range.toString());
    thenReturned("range(3.0, 5.0)");
  }
}
