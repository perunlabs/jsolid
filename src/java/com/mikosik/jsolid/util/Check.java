package com.mikosik.jsolid.util;

public class Check {
  public static double positive(double value) {
    if (value <= 0) {
      throw new IllegalArgumentException();
    }
    return value;
  }

  public static int positive(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException();
    }
    return value;
  }

  public static double notNegative(double value) {
    if (value < 0) {
      throw new IllegalArgumentException();
    }
    return value;
  }
}
