package com.perunlabs.jsolid.d3;

import static com.perunlabs.jsolid.JSolid.cuboid;
import static com.perunlabs.jsolid.JSolid.degrees;
import static com.perunlabs.jsolid.JSolid.maxZ;
import static com.perunlabs.jsolid.JSolid.range;
import static com.perunlabs.jsolid.JSolid.v;
import static com.perunlabs.jsolid.JSolid.x;
import static com.perunlabs.jsolid.JSolid.y;
import static com.perunlabs.jsolid.JSolid.z;
import static com.perunlabs.jsolid.d3.Vector3Matchers.closeTo;
import static org.testory.Testory.given;
import static org.testory.Testory.thenEqual;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class AxisTest {
  private Cuboid cuboid;
  private Matrix4 matrix;
  private Vector3 v;

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
  public void x_axis_range() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.X.range(cuboid));
    thenReturned(range(1, 2));
  }

  @Test
  public void y_axis_range() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Y.range(cuboid));
    thenReturned(range(3, 4));
  }

  @Test
  public void z_axis_range() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(Axis.Z.range(cuboid));
    thenReturned(range(5, 6));
  }

  @Test
  public void sizeX() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(1, 3), range(1, 4)));
    when(Axis.X.size(cuboid));
    thenReturned(1.0);
  }

  @Test
  public void sizeY() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(1, 3), range(1, 4)));
    when(Axis.Y.size(cuboid));
    thenReturned(2.0);
  }

  @Test
  public void sizeZ() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(1, 3), range(1, 4)));
    when(Axis.Z.size(cuboid));
    thenReturned(3.0);
  }

  @Test
  public void x_mirror_matrix() throws Exception {
    given(matrix = Axis.X.mirrorMatrix());
    when(matrix).mul(v(1, 2, 3));
    thenReturned(v(-1, 2, 3));
  }

  @Test
  public void y_mirror_matrix() throws Exception {
    given(matrix = Axis.Y.mirrorMatrix());
    when(matrix).mul(v(1, 2, 3));
    thenReturned(v(1, -2, 3));
  }

  @Test
  public void z_mirror_matrix() throws Exception {
    given(matrix = Axis.Z.mirrorMatrix());
    when(matrix).mul(v(1, 2, 3));
    thenReturned(v(1, 2, -3));
  }

  @Test
  public void rotate_x_by_0() throws Exception {
    given(v = v(1, 2, 3));
    when(x().rotateMatrix(degrees(0))).mul(v);
    thenReturned(closeTo(v(1, 2, 3)));
  }

  @Test
  public void rotate_x_by_90() throws Exception {
    given(v = v(1, 2, 3));
    when(x().rotateMatrix(degrees(90))).mul(v);
    thenReturned(closeTo(v(1, -3, 2)));
  }

  @Test
  public void rotate_x_by_180() throws Exception {
    given(v = v(1, 2, 3));
    when(x().rotateMatrix(degrees(180))).mul(v);
    thenReturned(closeTo(v(1, -2, -3)));
  }

  @Test
  public void rotate_x_by_270() throws Exception {
    given(v = v(1, 2, 3));
    when(x().rotateMatrix(degrees(270))).mul(v);
    thenReturned(closeTo(v(1, 3, -2)));
  }

  @Test
  public void rotate_x_by_360() throws Exception {
    given(v = v(1, 2, 3));
    when(x().rotateMatrix(degrees(360))).mul(v);
    thenReturned(closeTo(v(1, 2, 3)));
  }

  @Test
  public void rotate_y_by_0() throws Exception {
    given(v = v(1, 2, 3));
    when(y().rotateMatrix(degrees(0))).mul(v);
    thenReturned(closeTo(v(1, 2, 3)));
  }

  @Test
  public void rotate_y_by_90() throws Exception {
    given(v = v(1, 2, 3));
    when(y().rotateMatrix(degrees(90))).mul(v);
    thenReturned(closeTo(v(3, 2, -1)));
  }

  @Test
  public void rotate_y_by_180() throws Exception {
    given(v = v(1, 2, 3));
    when(y().rotateMatrix(degrees(180))).mul(v);
    thenReturned(closeTo(v(-1, 2, -3)));
  }

  @Test
  public void rotate_y_by_270() throws Exception {
    given(v = v(1, 2, 3));
    when(y().rotateMatrix(degrees(270))).mul(v);
    thenReturned(closeTo(v(-3, 2, 1)));
  }

  @Test
  public void rotate_y_by_360() throws Exception {
    given(v = v(1, 2, 3));
    when(y().rotateMatrix(degrees(360))).mul(v);
    thenReturned(closeTo(v(1, 2, 3)));
  }

  @Test
  public void rotate_z_by_0() throws Exception {
    given(v = v(1, 2, 3));
    when(z().rotateMatrix(degrees(0))).mul(v);
    thenReturned(closeTo(v(1, 2, 3)));
  }

  @Test
  public void rotate_z_by_90() throws Exception {
    given(v = v(1, 2, 3));
    when(z().rotateMatrix(degrees(90))).mul(v);
    thenReturned(closeTo(v(-2, 1, 3)));
  }

  @Test
  public void rotate_z_by_180() throws Exception {
    given(v = v(1, 2, 3));
    when(z().rotateMatrix(degrees(180))).mul(v);
    thenReturned(closeTo(v(-1, -2, 3)));
  }

  @Test
  public void rotate_z_by_270() throws Exception {
    given(v = v(1, 2, 3));
    when(z().rotateMatrix(degrees(270))).mul(v);
    thenReturned(closeTo(v(2, -1, 3)));
  }

  @Test
  public void rotate_z_by_360() throws Exception {
    given(v = v(1, 2, 3));
    when(z().rotateMatrix(degrees(360))).mul(v);
    thenReturned(closeTo(v(1, 2, 3)));
  }

  @Test
  public void rotate_multiple_times_with_multiplied_matrixes() throws Exception {
    given(matrix = z().rotateMatrix(degrees(90)).mul(y().rotateMatrix(degrees(90))).mul(x()
        .rotateMatrix(degrees(90))));
    when(matrix.mul(y()));
    thenReturned(closeTo(y()));
  }

  @Test
  public void scale_x_matrix() throws Exception {
    given(v = v(1, 2, 3));
    when(x().scaleMatrix(2)).mul(v);
    thenReturned(v(2, 2, 3));
  }

  @Test
  public void scale_y_matrix() throws Exception {
    given(v = v(1, 2, 3));
    when(y().scaleMatrix(2)).mul(v);
    thenReturned(v(1, 4, 3));
  }

  @Test
  public void scale_z_matrix() throws Exception {
    given(v = v(1, 2, 3));
    when(z().scaleMatrix(2)).mul(v);
    thenReturned(v(1, 2, 6));
  }

  @Test
  public void vectorIn() throws Exception {
    given(cuboid = cuboid(range(1, 2), range(3, 4), range(5, 6)));
    when(maxZ().vectorIn(cuboid));
    thenReturned(v(0, 0, 6));
  }
}
