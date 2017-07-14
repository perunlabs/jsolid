package com.perunlabs.jsolid.util;

import static java.util.Objects.requireNonNull;

public class Check {
  public static double positive(double value) {
    if (value <= 0) {
      throw new IllegalArgumentException(
          "Parameter is " + value + " but expected positive double.");
    }
    return value;
  }

  public static int positive(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException(
          "Parameter is " + value + " but expected positive int.");
    }
    return value;
  }

  public static double notNegative(double value) {
    if (value < 0) {
      throw new IllegalArgumentException(
          "Parameter is " + value + " but expected not negative double.");
    }
    return value;
  }

  public static double isFinite(double value) {
    if (Double.isFinite(value)) {
      return value;
    }
    throw new IllegalArgumentException(
        "Parameter is " + value + " but expected finite double.");
  }

  public static <T> T[] noNullElements(T[] array) {
    for (T element : array) {
      requireNonNull(element);
    }
    return array;
  }
}
