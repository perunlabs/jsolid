package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.d3.Vector3.vector3;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.thenThrown;
import static org.testory.Testory.when;

import org.junit.Test;

public class Vector3Test {
  private Vector3 vector;

  @Test
  public void length() throws Exception {
    given(vector = vector3(3, 4, 12));
    when(vector.length());
    thenReturned(13.0);
  }

  @Test
  public void add() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.add(vector3(5, 7, 11)));
    thenReturned(vector3(6, 9, 14));
  }

  @Test
  public void subtract() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.sub(vector3(5, 7, 11)));
    thenReturned(vector3(-4, -5, -8));
  }

  @Test
  public void negate() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.neg());
    thenReturned(vector3(-1, -2, -3));
  }

  @Test
  public void mul() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.mul(2));
    thenReturned(vector3(2, 4, 6));
  }

  @Test
  public void div() throws Exception {
    given(vector = vector3(2, 4, 6));
    when(vector.div(2));
    thenReturned(vector3(1, 2, 3));
  }

  @Test
  public void dot_from_perpendicular_vectors() throws Exception {
    given(vector = vector3(1, 1, 0));
    when(vector.dot(vector3(0, 0, 1)));
    thenReturned(0.0);
  }

  @Test
  public void dot_from_parallel_vectors() throws Exception {
    given(vector = vector3(2, 0, 0));
    when(vector.dot(vector3(3, 0, 0)));
    thenReturned(6.0);
  }

  @Test
  public void dot_product() throws Exception {
    given(vector = vector3(1, 2, 0));
    when(vector.dot(vector3(3, 5, 0)));
    thenReturned(13.0);
  }

  @Test
  public void cross_product_on_itself_is_zero() throws Exception {
    given(vector = vector3(1, 2, 0));
    when(vector.cross(vector));
    thenReturned(vector3(0.0, 0.0, 0.0));
  }

  @Test
  public void cross_product_on_negated_is_zero() throws Exception {
    given(vector = vector3(1, 2, 0));
    when(vector.cross(vector.neg()));
    thenReturned(vector3(0, 0, 0));
  }

  @Test
  public void cross_product_of_perpendicular_unit_vectors_is_perpendicular_to_them()
      throws Exception {
    given(vector = vector3(1, 0, 0));
    when(vector.cross(vector3(0, 1, 0)));
    thenReturned(vector3(0, 0, 1));
  }

  @Test
  public void normalize() throws Exception {
    given(vector = vector3(3, 4, 12));
    when(vector.normalize());
    thenReturned(vector3(3.0 / 13, 4.0 / 13, 12.0 / 13));
  }

  @Test
  public void interpolate_throws_exception_for_negative_value() throws Exception {
    given(vector = vector3(3, 4, 12));
    when(vector).interpolate(null, -1);
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void interpolate_throws_exception_for_greater_than_1_value() throws Exception {
    given(vector = vector3(3, 4, 12));
    when(vector).interpolate(null, 1.1);
    thenThrown(IllegalArgumentException.class);
  }

  @Test
  public void interpolate_x() throws Exception {
    given(vector = vector3(10, 0, 0));
    when(vector).interpolate(vector3(20, 0, 0), 0.3);
    thenReturned(vector3(13, 0, 0));
  }

  @Test
  public void interpolate_y() throws Exception {
    given(vector = vector3(0, 10, 0));
    when(vector).interpolate(vector3(0, 20, 0), 0.3);
    thenReturned(vector3(0, 13, 0));
  }

  @Test
  public void interpolate_z() throws Exception {
    given(vector = vector3(0, 0, 10));
    when(vector).interpolate(vector3(0, 0, 20), 0.3);
    thenReturned(vector3(0, 0, 13));
  }

  @Test
  public void is_equal_to_itself() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.equals(vector));
    thenReturned(true);
  }

  @Test
  public void is_equal_to_equal_vector() {
    given(vector = vector3(1, 2, 3));
    when(vector.equals(vector3(1, 2, 3)));
    thenReturned(true);
  }

  @Test
  public void is_not_equal_to_vector_with_different_x() {
    given(vector = vector3(1, 2, 3));
    when(vector.equals(vector3(4, 2, 3)));
    thenReturned(false);
  }

  @Test
  public void is_not_equal_to_vector_with_different_y() {
    given(vector = vector3(1, 2, 3));
    when(vector.equals(vector3(1, 4, 3)));
    thenReturned(false);
  }

  @Test
  public void is_not_equal_to_vector_with_different_z() {
    given(vector = vector3(1, 2, 3));
    when(vector.equals(vector3(1, 2, 4)));
    thenReturned(false);
  }

  @Test
  public void equal_vectors_have_same_hash_code() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.hashCode() == vector3(1, 2, 3).hashCode());
    thenReturned(true);
  }

  @Test
  public void different_vectors_have_different_hash_code() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.hashCode() == vector3(1, 2, 4).hashCode());
    thenReturned(false);
  }

  @Test
  public void to_string() throws Exception {
    given(vector = vector3(1, 2, 3));
    when(vector.toString());
    thenReturned("v(1.0, 2.0, 3.0)");
  }
}
