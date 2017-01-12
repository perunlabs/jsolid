package com.mikosik.jsolid.util;

import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class CheckTest {
  @Test
  public void positive_int_returns_value_when_positive() throws Exception {
    when(Check.positive(33));
    thenReturned(33);
  }

  @Test
  public void positive_int_throws_exception_when_negative() throws Exception {
    when(() -> Check.positive(-1));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void positive_int_throws_exception_when_zero() throws Exception {
    when(() -> Check.positive(0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void positive_double_returns_value_when_positive() throws Exception {
    when(Check.positive(33.0));
    thenReturned(33.0);
  }

  @Test
  public void positive_double_throws_exception_when_negative() throws Exception {
    when(() -> Check.positive(-1.0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void positive_double_throws_exception_when_zero() throws Exception {
    when(() -> Check.positive(0.0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void not_negative_double_returns_value_when_positive() throws Exception {
    when(Check.notNegative(33.0));
    thenReturned(33.0);
  }

  @Test
  public void not_negative_double_throws_exception_when_negative() throws Exception {
    when(() -> Check.notNegative(-1.0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void not_negative_double_throws_exception_when_zero() throws Exception {
    when(() -> Check.notNegative(0.0));
    thenReturned(0.0);
  }

  @Test
  public void is_finite_returns_value_if_it_is_number() throws Exception {
    when(() -> Check.isFinite(33));
    thenReturned(33.0);
  }

  @Test
  public void is_finite_throws_exception_when_argument_is_NaN() throws Exception {
    when(() -> Check.isFinite(Double.NaN));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void is_finite_throws_exception_when_argument_is_positive_infinity() throws Exception {
    when(() -> Check.isFinite(Double.POSITIVE_INFINITY));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void is_finite_throws_exception_when_argument_is_negative_infinity() throws Exception {
    when(() -> Check.isFinite(Double.NEGATIVE_INFINITY));
    thenThrown(IllegalArgumentException.class);
  }
}
