package com.perunlabs.jsolid.util;

import java.util.Arrays;

public class Hash {
  public static int hash(double... values) {
    return Arrays.hashCode(sanitize(values));
  }

  public static int hash(double value) {
    return Double.hashCode(sanitized(value));
  }

  private static double[] sanitize(double... values) {
    double[] copy = Arrays.copyOf(values, values.length);
    for (int i = 0; i < copy.length; i++) {
      copy[i] = sanitized(copy[i]);
    }
    return copy;
  }

  private static double sanitized(double value) {
    return value == -0.0 ? 0.0 : value;
  }
}
