package com.mikosik.jsolid;

public class Config {
  private double circleToPolygonPrecision = 0.004;

  public synchronized double getCircleToPolygonPrecision() {
    return circleToPolygonPrecision;
  }

  public synchronized void setCircleToPolygonPrecision(double value) {
    this.circleToPolygonPrecision = value;
  }
}
