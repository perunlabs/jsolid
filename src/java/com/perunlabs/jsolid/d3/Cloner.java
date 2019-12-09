package com.perunlabs.jsolid.d3;

@FunctionalInterface
public interface Cloner {
  public Solid clone(int index, Solid solid);
}
