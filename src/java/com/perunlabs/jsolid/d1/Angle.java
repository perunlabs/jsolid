package com.perunlabs.jsolid.d1;

import static java.lang.Math.PI;

import com.perunlabs.jsolid.util.Hash;

public class Angle {
  private static final int DEGREES_IN_PERIGON = 360;
  private static final double RADIANS_IN_PERIGON = 2 * PI;

  private final double perigon;

  private Angle(double perigons) {
    this.perigon = perigons;
  }

  public static Angle perigons(double perigons) {
    return new Angle(perigons);
  }

  public static Angle degrees(double degrees) {
    return new Angle(degrees / DEGREES_IN_PERIGON);
  }

  public static Angle radians(double radians) {
    return new Angle(radians / RADIANS_IN_PERIGON);
  }

  public double perigon() {
    return perigon;
  }

  public double degrees() {
    return DEGREES_IN_PERIGON * perigon;
  }

  public double radians() {
    return RADIANS_IN_PERIGON * perigon;
  }

  public boolean equals(Object object) {
    return (object instanceof Angle) && equals((Angle) object);
  }

  private boolean equals(Angle that) {
    return this.perigon == that.perigon;
  }

  public int hashCode() {
    return Hash.hash(perigon);
  }

  public String toString() {
    return Double.toString(perigon) + " perigons";
  }
}
