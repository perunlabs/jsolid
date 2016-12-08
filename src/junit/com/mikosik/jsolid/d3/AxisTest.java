package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.box;
import static com.mikosik.jsolid.JSolid.range;
import static com.mikosik.jsolid.JSolid.v;
import static org.testory.Testory.given;
import static org.testory.Testory.thenEqual;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class AxisTest {
  private Box box;

  @Test
  public void axis_x_equals_unit_vector() throws Exception {
    thenEqual(Axis.X, v(1, 0, 0));
  }

  @Test
  public void axis_y_equals_unit_vector() throws Exception {
    thenEqual(Axis.Y, v(0, 1, 0));
  }

  @Test
  public void axis_z_equals_unit_vector() throws Exception {
    thenEqual(Axis.Z, v(0, 0, 1));
  }

  @Test
  public void x_axis_coordinate() throws Exception {
    when(Axis.X.coordinate(v(1, 2, 3)));
    thenReturned(1.0);
  }

  @Test
  public void y_axis_coordinate() throws Exception {
    when(Axis.Y.coordinate(v(1, 2, 3)));
    thenReturned(2.0);
  }

  @Test
  public void z_axis_coordinate() throws Exception {
    when(Axis.Z.coordinate(v(1, 2, 3)));
    thenReturned(3.0);
  }

  @Test
  public void x_axis_vector() throws Exception {
    when(Axis.X.v(1));
    thenReturned(v(1, 0, 0));
  }

  @Test
  public void y_axis_vector() throws Exception {
    when(Axis.Y.v(1));
    thenReturned(v(0, 1, 0));
  }

  @Test
  public void z_axis_vector() throws Exception {
    when(Axis.Z.v(1));
    thenReturned(v(0, 0, 1));
  }

  @Test
  public void minX() throws Exception {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.X.min().valueIn(box));
    thenReturned(1.0);
  }

  @Test
  public void centerX() {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.X.center().valueIn(box));
    thenReturned(1.5);
  }

  @Test
  public void zeroX() {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.X.zero().valueIn(box));
    thenReturned(0.0);
  }

  @Test
  public void maxX() throws Exception {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.X.max().valueIn(box));
    thenReturned(2.0);
  }

  @Test
  public void sizeX() throws Exception {
    given(box = box(range(1, 2), range(1, 3), range(1, 4)));
    when(Axis.X.size(box));
    thenReturned(1.0);
  }

  @Test
  public void minY() throws Exception {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Y.min().valueIn(box));
    thenReturned(3.0);
  }

  @Test
  public void centerY() {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Y.center().valueIn(box));
    thenReturned(3.5);
  }

  @Test
  public void zeroY() {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Y.zero().valueIn(box));
    thenReturned(0.0);
  }

  @Test
  public void maxY() throws Exception {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Y.max().valueIn(box));
    thenReturned(4.0);
  }

  @Test
  public void sizeY() throws Exception {
    given(box = box(range(1, 2), range(1, 3), range(1, 4)));
    when(Axis.Y.size(box));
    thenReturned(2.0);
  }

  @Test
  public void minZ() throws Exception {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Z.min().valueIn(box));
    thenReturned(5.0);
  }

  @Test
  public void centerZ() {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Z.center().valueIn(box));
    thenReturned(5.5);
  }

  @Test
  public void zeroZ() {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Z.zero().valueIn(box));
    thenReturned(0.0);
  }

  @Test
  public void maxZ() throws Exception {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Z.max().valueIn(box));
    thenReturned(6.0);
  }

  @Test
  public void sizeZ() throws Exception {
    given(box = box(range(1, 2), range(1, 3), range(1, 4)));
    when(Axis.Z.size(box));
    thenReturned(3.0);
  }

  @Test
  public void vectorIn() throws Exception {
    given(box = box(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Z.max().vectorIn(box));
    thenReturned(v(0, 0, 6));
  }
}
