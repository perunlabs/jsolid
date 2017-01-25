package com.mikosik.jsolid.d3;

public interface Alignment {

  public Solid align(Solid solid1, Solid solid2);

  public Vector3 alignShiftFor(Solid solid1, Solid solid2);
}
