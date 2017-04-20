package com.mikosik.jsolid.d3;

import java.util.Objects;

public class Edge {
  public final Vector3 pointA;
  public final Vector3 pointB;

  public Edge(Vector3 pointA, Vector3 pointB) {
    this.pointA = pointA;
    this.pointB = pointB;
  }

  public Edge flip() {
    return new Edge(pointB, pointA);
  }

  public boolean equals(Object object) {
    return object instanceof Edge && equals((Edge) object);
  }

  private boolean equals(Edge that) {
    return eq(that);
  }

  private boolean eq(Edge that) {
    return this.pointA.equals(that.pointA) && this.pointB.equals(that.pointB);
  }

  public int hashCode() {
    return Objects.hash(pointA, pointB);
  }

  public String toString() {
    return "edge(" + pointA + ", " + pointB + ")";
  }
}
