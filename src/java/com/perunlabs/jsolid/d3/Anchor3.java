package com.perunlabs.jsolid.d3;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.perunlabs.jsolid.d1.Anchor1;

public class Anchor3<A extends Axis<A>> {
  public final Axis<A> axis;
  private final Anchor1 anchor;

  public Anchor3(Axis<A> axis, Anchor1 anchor) {
    this.axis = axis;
    this.anchor = anchor;
  }

  public double valueIn(Solid solid) {
    List<Double> elements = solid.vertexes().stream()
        .map(v -> axis.coordinate(v))
        .collect(toList());
    return anchor.of(elements);
  }

  public Anchor3<A> other() {
    return new Anchor3<>(axis, anchor.opposite());
  }

  public double edge() {
    return anchor.edge;
  }

  public Vector3 vectorIn(Solid solid) {
    return axis.v(valueIn(solid));
  }
}
