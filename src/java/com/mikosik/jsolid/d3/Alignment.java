package com.mikosik.jsolid.d3;

import eu.mihosoft.vrl.v3d.Vector3d;

public class Alignment<A extends Axis<A>> {
  private final Anchor<A> anchor1;
  private final Anchor<A> anchor2;

  public Alignment(Anchor<A> anchor1, Anchor<A> anchor2) {
    this.anchor1 = anchor1;
    this.anchor2 = anchor2;
  }

  public Solid align(Solid solid1, Solid solid2) {
    return solid2.move(alignShiftFor(solid1, solid2));
  }

  public Vector3d alignShiftFor(Solid solid1, Solid solid2) {
    return anchor1.axis.v(shiftDeltaFor(solid1, solid2));
  }

  private double shiftDeltaFor(Solid solid1, Solid solid2) {
    return anchor1.positionIn(solid1) - anchor2.positionIn(solid2);
  }
}
