package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.d1.Anchor1.CENTER;
import static com.mikosik.jsolid.d1.Anchor1.MAX;
import static com.mikosik.jsolid.d1.Anchor1.MIN;
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
  public void set_low() throws Exception {
    given(range = new Range(3, 5));
    when(range.low(1));
    thenReturned(new Range(1, 5));
  }

  @Test
  public void set_low_with_low_equal_to_high() throws Exception {
    given(range = new Range(3, 5));
    when(range.low(5));
    thenReturned(new Range(5, 5));
  }

  @Test
  public void set_low_fails_when_it_is_greater_than_high() throws Exception {
    given(range = new Range(3, 5));
    when(() -> range.low(6));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void set_high() throws Exception {
    given(range = new Range(3, 5));
    when(range.high(7));
    thenReturned(new Range(3, 7));
  }

  public void set_high_with_low_equal_to_high() throws Exception {
    given(range = new Range(3, 5));
    when(range.high(3));
    thenReturned(new Range(3, 3));
  }

  @Test
  public void set_high_fails_when_it_is_lower_than_low() throws Exception {
    given(range = new Range(3, 5));
    when(() -> range.high(2));
    thenThrown(IllegalArgumentException.class);
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
  public void moveBy() throws Exception {
    given(range = new Range(3, 5));
    when(range.moveBy(7));
    thenReturned(new Range(10, 12));
  }

  @Test
  public void move_to_center() throws Exception {
    given(range = new Range(3, 5));
    when(range.moveTo(CENTER, 7));
    thenReturned(new Range(6, 8));
  }

  @Test
  public void move_to_min() throws Exception {
    given(range = new Range(3, 5));
    when(range.moveTo(MIN, 7));
    thenReturned(new Range(7, 9));
  }

  @Test
  public void move_to_max() throws Exception {
    given(range = new Range(3, 5));
    when(range.moveTo(MAX, 7));
    thenReturned(new Range(5, 7));
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
