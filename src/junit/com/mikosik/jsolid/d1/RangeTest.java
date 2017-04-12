package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.JSolid.center;
import static com.mikosik.jsolid.JSolid.max;
import static com.mikosik.jsolid.JSolid.min;
import static com.mikosik.jsolid.JSolid.range;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class RangeTest {
  private Range range;

  @Test
  public void min_is_calculated_from_constructor_size_param() throws Exception {
    given(range = range(10));
    when(range.min);
    thenReturned(-5.0);
  }

  @Test
  public void max_is_calculated_from_constructor_size_param() throws Exception {
    given(range = range(10));
    when(range.max);
    thenReturned(5.0);
  }

  @Test
  public void min_returns_first_when_it_is_miner() throws Exception {
    given(range = range(3, 5));
    when(range.min);
    thenReturned(3.0);
  }

  @Test
  public void min_returns_second_when_it_is_miner() throws Exception {
    given(range = range(5, 3));
    when(range.min);
    thenReturned(3.0);
  }

  @Test
  public void max_returns_first_when_it_is_maxer() throws Exception {
    given(range = range(5, 3));
    when(range.max);
    thenReturned(5.0);
  }

  @Test
  public void max_returns_second_when_it_is_maxer() throws Exception {
    given(range = range(3, 5));
    when(range.max);
    thenReturned(5.0);
  }

  @Test
  public void size() throws Exception {
    given(range = range(3, 7));
    when(range.size());
    thenReturned(4.0);
  }

  @Test
  public void size_for_v2_miner_than_v1() throws Exception {
    given(range = range(7, 3));
    when(range.size());
    thenReturned(4.0);
  }

  @Test
  public void center_anchor() throws Exception {
    given(range = range(3, 7));
    when(range.center());
    thenReturned(5.0);
  }

  @Test
  public void center_for_v2_miner_than_v1() throws Exception {
    given(range = range(7, 3));
    when(range.center());
    thenReturned(5.0);
  }

  @Test
  public void set_min() throws Exception {
    given(range = range(3, 5));
    when(range.min(1));
    thenReturned(range(1, 5));
  }

  @Test
  public void set_min_with_min_equal_to_max() throws Exception {
    given(range = range(3, 5));
    when(range.min(5));
    thenReturned(range(5, 5));
  }

  @Test
  public void set_min_fails_when_it_is_greater_than_max() throws Exception {
    given(range = range(3, 5));
    when(() -> range.min(6));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void set_max() throws Exception {
    given(range = range(3, 5));
    when(range.max(7));
    thenReturned(range(3, 7));
  }

  public void set_max_with_min_equal_to_max() throws Exception {
    given(range = range(3, 5));
    when(range.max(3));
    thenReturned(range(3, 3));
  }

  @Test
  public void set_max_fails_when_it_is_miner_than_min() throws Exception {
    given(range = range(3, 5));
    when(() -> range.max(2));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(10));
    thenReturned(range(-1, 9));
  }

  @Test
  public void resize_to_zero() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(0));
    thenReturned(range(4, 4));
  }

  @Test
  public void resize_to_fails_for_negative_value() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeTo(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_min() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(10, min()));
    thenReturned(range(3, 13));
  }

  @Test
  public void resize_to_zero_min() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(0, min()));
    thenReturned(range(3, 3));
  }

  @Test
  public void resize_to_min_fails_for_negative_value() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeTo(-1, min()));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_max() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(10, max()));
    thenReturned(range(-5, 5));
  }

  @Test
  public void resize_to_zero_max() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(0, max()));
    thenReturned(range(5, 5));
  }

  @Test
  public void resize_to_max_fails_for_negative_value() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeTo(-1, max()));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_center() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(10, center()));
    thenReturned(range(-1, 9));
  }

  @Test
  public void resize_to_zero_center() throws Exception {
    given(range = range(3, 5));
    when(range.resizeTo(0, center()));
    thenReturned(range(4, 4));
  }

  @Test
  public void resize_to_center_fails_for_negative_value() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeTo(-1, center()));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by() throws Exception {
    given(range = range(3, 5));
    when(range.resizeBy(2));
    thenReturned(range(2, 6));
  }

  @Test
  public void resize_by_zero() throws Exception {
    given(range = range(3, 5));
    when(range.resizeBy(0));
    thenReturned(range(3, 5));
  }

  @Test
  public void resize_by_negative_value() throws Exception {
    given(range = range(3, 5));
    when(range.resizeBy(-1));
    thenReturned(range(3.5, 4.5));
  }

  @Test
  public void resize_by_to_zero() throws Exception {
    given(range = range(3, 5));
    when(range.resizeBy(-2));
    thenReturned(range(4, 4));
  }

  @Test
  public void resize_by_bemin_zero_fails() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(-3));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_min_fails_for_negative_size() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(-2.1, min()));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_min_to_zero() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(-2, min()));
    thenReturned(range(3, 3));
  }

  @Test
  public void resize_by_min() throws Exception {
    given(range = range(3, 5));
    when(range.resizeBy(1, min()));
    thenReturned(range(3, 6));
  }

  @Test
  public void resize_by_max_fails_for_negative_size() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(-2.1, max()));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_max_to_zero() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(-2, max()));
    thenReturned(range(5, 5));
  }

  @Test
  public void resize_by_max() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(1, max()));
    thenReturned(range(2, 5));
  }

  @Test
  public void resize_by_center_fails_for_negative_size() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(-2.1, center()));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_center_to_zero() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(-2, center()));
    thenReturned(range(4, 4));
  }

  @Test
  public void resize_by_center() throws Exception {
    given(range = range(3, 5));
    when(() -> range.resizeBy(2, center()));
    thenReturned(range(2, 6));
  }

  @Test
  public void moveBy() throws Exception {
    given(range = range(3, 5));
    when(range.moveBy(7));
    thenReturned(range(10, 12));
  }

  @Test
  public void move_to_center() throws Exception {
    given(range = range(3, 5));
    when(range.moveTo(center(), 7));
    thenReturned(range(6, 8));
  }

  @Test
  public void move_to_min() throws Exception {
    given(range = range(3, 5));
    when(range.moveTo(min(), 7));
    thenReturned(range(7, 9));
  }

  @Test
  public void move_to_max() throws Exception {
    given(range = range(3, 5));
    when(range.moveTo(max(), 7));
    thenReturned(range(5, 7));
  }

  @Test
  public void mul() throws Exception {
    given(range = range(3, 5));
    when(range.mul(2));
    thenReturned(range(2, 6));
  }

  @Test
  public void mul_by_negative_not_almined() throws Exception {
    given(range = range(3, 5));
    when(() -> range.mul(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void negate() throws Exception {
    given(range = range(-3, 5));
    when(() -> range.neg());
    thenReturned(range(-5, 3));
  }

  @Test
  public void equal_ranges() throws Exception {
    given(range = range(3, 5));
    when(range.equals(range(3, 5)));
    thenReturned(true);
  }

  @Test
  public void not_equal_min_value() throws Exception {
    given(range = range(3, 5));
    when(range.equals(range(4, 5)));
    thenReturned(false);
  }

  @Test
  public void not_equal_max_value() throws Exception {
    given(range = range(3, 5));
    when(range.equals(range(3, 6)));
    thenReturned(false);
  }

  @Test
  public void to_string() throws Exception {
    given(range = range(3, 5));
    when(range.toString());
    thenReturned("range(3.0, 5.0)");
  }
}
