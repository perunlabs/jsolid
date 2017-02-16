package com.mikosik.jsolid.d3;

public class AxisAlignment<A extends Axis<A>> implements Alignment {
  private final Anchor<A> anchor1;
  private final Anchor<A> anchor2;

  public AxisAlignment(Anchor<A> anchor1, Anchor<A> anchor2) {
    this.anchor1 = anchor1;
    this.anchor2 = anchor2;
  }

  public Solid align(Solid solid1, Solid solid2) {
    return solid2.moveBy(alignShiftFor(solid1, solid2));
  }

  public Vector3 alignShiftFor(Solid solid1, Solid solid2) {
    return anchor1.vectorIn(solid1).sub(anchor2.vectorIn(solid2));
  }
}
