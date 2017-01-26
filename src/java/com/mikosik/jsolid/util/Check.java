package com.mikosik.jsolid.util;

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
}
