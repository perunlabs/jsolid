package com.mikosik.jsolid.d3;

public class Alignment {
  private final Anchor3<?> anchor1;
  private final Anchor3<?> anchor2;
  private final double shift;

  public <A extends Axis<A>> Alignment(Anchor3<A> anchor1, Anchor3<A> anchor2, double shift) {
    this.anchor1 = anchor1;
    this.anchor2 = anchor2;
    this.shift = shift;
  }

  public Solid align(Solid solid1, Solid solid2) {
    return solid2.moveBy(alignShiftFor(solid1, solid2));
  }

  public Vector3 alignShiftFor(Solid solid1, Solid solid2) {
    return anchor1.vectorIn(solid1).sub(anchor2.vectorIn(solid2)).add(anchor1.axis.v(shift));
  }
}
