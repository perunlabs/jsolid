package com.mikosik.jsolid.d1;

import static com.mikosik.jsolid.d1.Anchor1.CENTER;
import static com.mikosik.jsolid.d1.Anchor1.MAX;
import static com.mikosik.jsolid.d1.Anchor1.MIN;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class Anchor1Test {
  private Range range;

  @Test
  public void move_to_min() throws Exception {
    given(range = new Range(3, 5));
    when(MIN.moveTo(range, 7));
    thenReturned(new Range(7, 9));
  }

  @Test
  public void move_to_center() throws Exception {
    given(range = new Range(3, 5));
    when(CENTER.moveTo(range, 7));
    thenReturned(new Range(6, 8));
  }

  @Test
  public void move_to_max() throws Exception {
    given(range = new Range(3, 5));
    when(MAX.moveTo(range, 7));
    thenReturned(new Range(5, 7));
  }

  @Test
  public void resize_to_min_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> MIN.resizeTo(range, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_min() throws Exception {
    given(range = new Range(3, 5));
    when(MIN.resizeTo(range, 6));
    thenReturned(new Range(3, 9));
  }

  @Test
  public void resize_to_max_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> MAX.resizeTo(range, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_max() throws Exception {
    given(range = new Range(3, 5));
    when(MAX.resizeTo(range, 6));
    thenReturned(new Range(-1, 5));
  }

  @Test
  public void resize_to_center_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> CENTER.resizeTo(range, -1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_to_center() throws Exception {
    given(range = new Range(3, 5));
    when(CENTER.resizeTo(range, 6));
    thenReturned(new Range(1, 7));
  }

  @Test
  public void resize_by_min_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> MIN.resizeBy(range, -2.1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_min_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(MIN.resizeBy(range, -2));
    thenReturned(new Range(3, 3));
  }

  @Test
  public void resize_by_min() throws Exception {
    given(range = new Range(3, 5));
    when(MIN.resizeBy(range, 1));
    thenReturned(new Range(3, 6));
  }

  @Test
  public void resize_by_max_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> MAX.resizeBy(range, -2.1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_max_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(MAX.resizeBy(range, -2));
    thenReturned(new Range(5, 5));
  }

  @Test
  public void resize_by_max() throws Exception {
    given(range = new Range(3, 5));
    when(MAX.resizeBy(range, 1));
    thenReturned(new Range(2, 5));
  }

  @Test
  public void resize_by_center_fails_for_negative_size() throws Exception {
    given(range = new Range(3, 5));
    when(() -> CENTER.resizeBy(range, -2.1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void resize_by_center_to_zero() throws Exception {
    given(range = new Range(3, 5));
    when(CENTER.resizeBy(range, -2));
    thenReturned(new Range(4, 4));
  }

  @Test
  public void resize_by_center() throws Exception {
    given(range = new Range(3, 5));
    when(CENTER.resizeBy(range, 2));
    thenReturned(new Range(2, 6));
  }
}
