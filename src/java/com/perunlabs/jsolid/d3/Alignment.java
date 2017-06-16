package com.perunlabs.jsolid.d3;

import static java.util.Objects.requireNonNull;

public class Alignment {
  private final Anchor3<?> anchorA;
  private final Anchor3<?> anchorB;
  private final double shift;

  public <A extends Axis<A>> Alignment(Anchor3<A> anchorA, Anchor3<A> anchorB, double shift) {
    this.anchorA = requireNonNull(anchorA);
    this.anchorB = requireNonNull(anchorB);
    this.shift = shift;
  }

  public Solid align(Solid solid1, Solid solid2) {
    return solid2.moveBy(alignShiftFor(solid1, solid2));
  }

  public Vector3 alignShiftFor(Solid solid1, Solid solid2) {
    return anchorA.vectorIn(solid1).sub(anchorB.vectorIn(solid2)).add(anchorA.axis.v(shift));
  }
}
