package com.perunlabs.jsolid.d1;

import static com.perunlabs.jsolid.JSolid.center;
import static com.perunlabs.jsolid.JSolid.max;
import static com.perunlabs.jsolid.JSolid.min;
import static java.util.Arrays.asList;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class Anchor1Test {
  private Range range;
  private Iterable<Double> stream;

  @Test
  public void min_edge() throws Exception {
    when(min().edge);
    thenReturned(-1.0);
  }

  @Test
  public void center_edge() throws Exception {
    when(center().edge);
    thenReturned(0.0);
  }

  @Test
  public void max_edge() throws Exception {
    when(max().edge);
    thenReturned(1.0);
  }

  @Test
  public void move_to_min() throws Exception {
    given(range = new Range(3, 5));
    when(min().moveTo(range, 7));
    thenReturned(new Range(7, 9));
  }

  @Test
  public void move_to_center() throws Exception {
    given(range = new Range(3, 5));
    when(center().moveTo(range, 7));
    thenReturned(new Range(6, 8));
  }

  @Test
  public void move_to_max() throws Exception {
    given(range = new Range(3, 5));
    when(max().moveTo(range, 7));
    thenReturned(new Range(5, 7));
  }

  @Test
  public void resize_to_min_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> min().resizeTo(range, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_min() throws Exception {
    given(range = new Range(3, 5));
    when(min().resizeTo(range, 6));
    thenReturned(new Range(3, 9));
  }

  @Test
  public void resize_to_max_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> max().resizeTo(range, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_max() throws Exception {
    given(range = new Range(3, 5));
    when(max().resizeTo(range, 6));
    thenReturned(new Range(-1, 5));
  }

  @Test
  public void resize_to_center_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> center().resizeTo(range, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_center() throws Exception {
    given(range = new Range(3, 5));
    when(center().resizeTo(range, 6));
    thenReturned(new Range(1, 7));
  }

  @Test
  public void resize_by_min_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> min().resizeBy(range, -2.1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_min_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(min().resizeBy(range, -2));
    thenReturned(new Range(3, 3));
  }

  @Test
  public void resize_by_min() throws Exception {
    given(range = new Range(3, 5));
    when(min().resizeBy(range, 1));
    thenReturned(new Range(3, 6));
  }

  @Test
  public void resize_by_max_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> max().resizeBy(range, -2.1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_max_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(max().resizeBy(range, -2));
    thenReturned(new Range(5, 5));
  }

  @Test
  public void resize_by_max() throws Exception {
    given(range = new Range(3, 5));
    when(max().resizeBy(range, 1));
    thenReturned(new Range(2, 5));
  }

  @Test
  public void resize_by_center_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> center().resizeBy(range, -2.1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_center_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(center().resizeBy(range, -2));
    thenReturned(new Range(4, 4));
  }

  @Test
  public void resize_by_center() throws Exception {
    given(range = new Range(3, 5));
    when(center().resizeBy(range, 2));
    thenReturned(new Range(2, 6));
  }

  @Test
  public void min_of_many_values() throws Exception {
    given(stream = asList(-3.0, 0.0, 3.0));
    when(() -> min().of(stream));
    thenReturned(-3.0);
  }

  @Test
  public void min_of_one_value() throws Exception {
    given(stream = asList(3.0));
    when(() -> min().of(stream));
    thenReturned(3.0);
  }

  @Test
  public void min_of_empty_throws_exception() throws Exception {
    given(stream = asList());
    when(() -> min().of(stream));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void max_of_many_values() throws Exception {
    given(stream = asList(-3.0, 0.0, 3.0));
    when(() -> max().of(stream));
    thenReturned(3.0);
  }

  @Test
  public void max_of_one_value() throws Exception {
    given(stream = asList(3.0));
    when(() -> min().of(stream));
    thenReturned(3.0);
  }

  @Test
  public void max_of_empty_throws_exception() throws Exception {
    given(stream = asList());
    when(() -> max().of(stream));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void center_of_many_values() throws Exception {
    given(stream = asList(-3.0, 0.0, 7.0));
    when(() -> center().of(stream));
    thenReturned(2.0);
  }

  @Test
  public void center_of_one_value() throws Exception {
    given(stream = asList(3.0));
    when(() -> center().of(stream));
    thenReturned(3.0);
  }

  @Test
  public void center_of_empty_throws_exception() throws Exception {
    given(stream = asList());
    when(() -> center().of(stream));
    thenThrown(IllegalArgumentException.class);
  }
}
