package com.perunlabs.jsolid.util;

import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.hamcrest.Matcher;
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
    thenThrown(iae("Parameter is -1 but expected positive int."));
  }

  @Test
  public void positive_int_throws_exception_when_zero() throws Exception {
    when(() -> Check.positive(0));
    thenThrown(iae("Parameter is 0 but expected positive int."));
  }

  @Test
  public void positive_double_returns_value_when_positive() throws Exception {
    when(Check.positive(33.0));
    thenReturned(33.0);
  }

  @Test
  public void positive_double_throws_exception_when_negative() throws Exception {
    when(() -> Check.positive(-1.0));
    thenThrown(iae("Parameter is -1.0 but expected positive double."));
  }

  @Test
  public void positive_double_throws_exception_when_zero() throws Exception {
    when(() -> Check.positive(0.0));
    thenThrown(iae("Parameter is 0.0 but expected positive double."));
  }

  @Test
  public void not_negative_double_returns_value_when_positive() throws Exception {
    when(Check.notNegative(33.0));
    thenReturned(33.0);
  }

  @Test
  public void not_negative_double_throws_exception_when_negative() throws Exception {
    when(() -> Check.notNegative(-1.0));
    thenThrown(iae("Parameter is -1.0 but expected not negative double."));
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
    thenThrown(iae("Parameter is NaN but expected finite double."));
  }

  @Test
  public void is_finite_throws_exception_when_argument_is_positive_infinity() throws Exception {
    when(() -> Check.isFinite(Double.POSITIVE_INFINITY));
    thenThrown(iae("Parameter is Infinity but expected finite double."));
  }

  @Test
  public void is_finite_throws_exception_when_argument_is_negative_infinity() throws Exception {
    when(() -> Check.isFinite(Double.NEGATIVE_INFINITY));
    thenThrown(iae("Parameter is -Infinity but expected finite double."));
  }

  @Test
  public void noNullElements_returns_argument() throws Exception {
    String[] array = new String[] { "abc", "def" };
    when(() -> Check.noNullElements(array));
    thenReturned(array);
  }

  @Test
  public void noNullElements_fails_when_element_is_null() throws Exception {
    when(() -> Check.noNullElements(new String[] { "abc", null }));
    thenThrown(NullPointerException.class);
  }

  private static Matcher<Throwable> iae(String message) {
    return ExceptionMatcher.exception(new IllegalArgumentException(message));
  }
}
