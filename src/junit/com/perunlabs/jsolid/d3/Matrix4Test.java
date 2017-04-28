package com.perunlabs.jsolid.d3;

import static com.perunlabs.jsolid.JSolid.v;
import static java.lang.Double.NaN;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class Matrix4Test {
  private Matrix4 matrix;
  private Matrix4 matrix2;
  private Vector3 v;

  @Test
  public void a11_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        NaN, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a12_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, NaN, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a13_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, NaN, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a14_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, NaN,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a21_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        NaN, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a22_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, NaN, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a23_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, NaN, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a24_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, NaN,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a31_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        NaN, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a32_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, NaN, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a33_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, NaN, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a34_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, NaN,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a41_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        NaN, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a42_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, NaN, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a43_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, NaN, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a44_must_not_be_NaN() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, NaN));
    thenThrown(IllegalArgumentException.class);
  }

  ///

  @Test
  public void a11_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        POSITIVE_INFINITY, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a12_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, POSITIVE_INFINITY, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a13_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, POSITIVE_INFINITY, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a14_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, POSITIVE_INFINITY,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a21_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        POSITIVE_INFINITY, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a22_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, POSITIVE_INFINITY, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a23_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, POSITIVE_INFINITY, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a24_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, POSITIVE_INFINITY,
        0, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a31_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        POSITIVE_INFINITY, 0, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a32_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, POSITIVE_INFINITY, 0, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a33_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, POSITIVE_INFINITY, 0,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a34_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, POSITIVE_INFINITY,
        0, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a41_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        POSITIVE_INFINITY, 0, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a42_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, POSITIVE_INFINITY, 0, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a43_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, POSITIVE_INFINITY, 0));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void a44_must_not_be_infinity() throws Exception {
    when(() -> new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, POSITIVE_INFINITY));
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void move() throws Exception {
    given(v = v(1, 2, 3));
    when(Matrix4.move(v(5, 7, 11))).mul(v);
    thenReturned(v(6, 9, 14));
  }

  @Test
  public void determinant_of_identity_matrix() throws Exception {
    given(matrix = new Matrix4(
        1, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 1));
    when(matrix).determinant();
    thenReturned(1.0);
  }

  @Test
  public void determinant_is_multiplied_by_2_when_matrix_column_is_multiplied_by_2()
      throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        5, 6, 7, 8,
        9, 10, 11, 12,
        12, 14, 15, 16));
    given(matrix2 = new Matrix4(
        2, 2, 3, 4,
        10, 6, 7, 8,
        18, 10, 11, 12,
        24, 14, 15, 16));
    when(matrix2).determinant();
    thenReturned(2 * matrix.determinant());
  }

  @Test
  public void determinant_of_multiplied_matrixes_is_equal_to_multiplied_determinants()
      throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        5, 6, 7, 8,
        9, 10, 11, 12,
        12, 14, 15, 16));
    given(matrix2 = new Matrix4(
        1, 5, 9, 13,
        2, 6, 10, 14,
        3, 7, 11, 15,
        4, 8, 12, 16));
    when(matrix.mul(matrix2)).determinant();
    thenReturned(matrix.determinant() * matrix2.determinant());
  }

  @Test
  public void determinant_of_matrix_with_zero_column_is_zero() throws Exception {
    given(matrix = new Matrix4(
        1, 0, 3, 4,
        5, 0, 7, 8,
        9, 0, 11, 12,
        12, 0, 15, 16));
    when(matrix).determinant();
    thenReturned(0.0);
  }

  @Test
  public void determinant_of_matrix_with_zero_row_is_zero() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        0, 0, 0, 0,
        9, 10, 11, 12,
        12, 13, 15, 16));
    when(matrix).determinant();
    thenReturned(0.0);
  }

  @Test
  public void mul_by_vector_x_component() throws Exception {
    given(matrix = new Matrix4(
        1, 0, 0, 0,
        2, 0, 0, 0,
        3, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(v(2, 0, 0)));
    thenReturned(v(2, 4, 6));
  }

  @Test
  public void mul_by_vector_y_component() throws Exception {
    given(matrix = new Matrix4(
        0, 1, 0, 0,
        0, 2, 0, 0,
        0, 3, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(v(0, 2, 0)));
    thenReturned(v(2, 4, 6));
  }

  @Test
  public void mul_by_vector_z_component() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 1, 0,
        0, 0, 2, 0,
        0, 0, 3, 0,
        0, 0, 0, 0));
    when(matrix.mul(v(0, 0, 2)));
    thenReturned(v(2, 4, 6));
  }

  @Test
  public void x_result_mul_by_vector() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 100,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(v(1, 2, 3)));
    thenReturned(v(114, 0, 0));
  }

  @Test
  public void y_result_mul_by_vector() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        1, 2, 3, 100,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(v(1, 2, 3)));
    thenReturned(v(0, 114, 0));
  }

  @Test
  public void z_result_mul_by_vector() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 100,
        0, 0, 0, 0));
    when(matrix.mul(v(1, 2, 3)));
    thenReturned(v(0, 0, 114));
  }

  @Test
  public void mul_by_vector_ignores_last_row() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 1, 1, 1));
    when(matrix.mul(v(1, 2, 3)));
    thenReturned(v(0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m11() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        1, 0, 0, 0,
        2, 0, 0, 0,
        3, 0, 0, 0,
        4, 0, 0, 0)));
    thenReturned(new Matrix4(
        30, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m12() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 1, 0, 0,
        0, 2, 0, 0,
        0, 3, 0, 0,
        0, 4, 0, 0)));
    thenReturned(new Matrix4(
        0, 30, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m13() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 0, 1, 0,
        0, 0, 2, 0,
        0, 0, 3, 0,
        0, 0, 4, 0)));
    thenReturned(new Matrix4(
        0, 0, 30, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m14() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 0, 0, 1,
        0, 0, 0, 2,
        0, 0, 0, 3,
        0, 0, 0, 4)));
    thenReturned(new Matrix4(
        0, 0, 0, 30,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m21() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        1, 0, 0, 0,
        2, 0, 0, 0,
        3, 0, 0, 0,
        4, 0, 0, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        30, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m22() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 1, 0, 0,
        0, 2, 0, 0,
        0, 3, 0, 0,
        0, 4, 0, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 30, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m23() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 0, 1, 0,
        0, 0, 2, 0,
        0, 0, 3, 0,
        0, 0, 4, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 30, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m24() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 0, 0, 1,
        0, 0, 0, 2,
        0, 0, 0, 3,
        0, 0, 0, 4)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 30,
        0, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m31() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        1, 0, 0, 0,
        2, 0, 0, 0,
        3, 0, 0, 0,
        4, 0, 0, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        30, 0, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m32() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 1, 0, 0,
        0, 2, 0, 0,
        0, 3, 0, 0,
        0, 4, 0, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 30, 0, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m33() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 0, 1, 0,
        0, 0, 2, 0,
        0, 0, 3, 0,
        0, 0, 4, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 30, 0,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m34() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4,
        0, 0, 0, 0));
    when(matrix.mul(new Matrix4(
        0, 0, 0, 1,
        0, 0, 0, 2,
        0, 0, 0, 3,
        0, 0, 0, 4)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 30,
        0, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m41() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4));
    when(matrix.mul(new Matrix4(
        1, 0, 0, 0,
        2, 0, 0, 0,
        3, 0, 0, 0,
        4, 0, 0, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        30, 0, 0, 0));
  }

  @Test
  public void mul_by_matrix_m42() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4));
    when(matrix.mul(new Matrix4(
        0, 1, 0, 0,
        0, 2, 0, 0,
        0, 3, 0, 0,
        0, 4, 0, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 30, 0, 0));
  }

  @Test
  public void mul_by_matrix_m43() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4));
    when(matrix.mul(new Matrix4(
        0, 0, 1, 0,
        0, 0, 2, 0,
        0, 0, 3, 0,
        0, 0, 4, 0)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 30, 0));
  }

  @Test
  public void mul_by_matrix_m44() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 2, 3, 4));
    when(matrix.mul(new Matrix4(
        0, 0, 0, 1,
        0, 0, 0, 2,
        0, 0, 0, 3,
        0, 0, 0, 4)));
    thenReturned(new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 30));
  }

  @Test
  public void equal() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        5, 6, 7, 8,
        9, 10, 11, 12,
        13, 14, 15, 16));
    given(matrix2 = new Matrix4(
        1, 2, 3, 4,
        5, 6, 7, 8,
        9, 10, 11, 12,
        13, 14, 15, 16));
    when(matrix.equals(matrix2));
    thenReturned(true);
  }

  @Test
  public void not_equal_when_m11_not_equal() throws Exception {
    given(matrix = new Matrix4(
        1, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m12_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 1, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m13_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 1, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m14_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 1,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m21_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        1, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m22_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m23_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m24_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 1,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m31_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m32_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 1, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m33_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 1, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m34_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 1,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m41_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        1, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m42_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 1, 0, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m43_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 1, 0));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void not_equal_when_m44_not_equal() throws Exception {
    given(matrix = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 1));
    given(matrix2 = new Matrix4(
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(false);
  }

  @Test
  public void matrixes_with_positive_and_negative_zero_are_equal() throws Exception {
    given(matrix = new Matrix4(
        -0.0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0.0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.equals(matrix2));
    thenReturned(true);
  }

  @Test
  public void matrixes_with_positive_and_negative_zero_have_same_hash_code() throws Exception {
    given(matrix = new Matrix4(
        -0.0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    given(matrix2 = new Matrix4(
        0.0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0));
    when(matrix.hashCode() == matrix2.hashCode());
    thenReturned(true);
  }

  @Test
  public void to_string() throws Exception {
    given(matrix = new Matrix4(
        1, 2, 3, 4,
        5, 6, 7, 8,
        9, 10, 11, 12,
        13, 14, 15, 16));
    when(matrix).toString();
    thenReturned("1.0 2.0 3.0 4.0\n"
        + "5.0 6.0 7.0 8.0\n"
        + "9.0 10.0 11.0 12.0\n"
        + "13.0 14.0 15.0 16.0");
  }
}
