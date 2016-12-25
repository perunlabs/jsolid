package com.mikosik.jsolid.d2;

import static com.mikosik.jsolid.d2.Vector2.vector2;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import static org.testory.Testory.given;
import static org.testory.Testory.then;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class Vector2Test {
  private Vector2 vector1;
  private Vector2 vector2;

  @Test
  public void length_of_zero_vector_is_zero() throws Exception {
    given(vector1 = vector2(0, 0));
    when(vector1.length());
    thenReturned(0.0);
  }

  @Test
  public void length_of_unit_vector_along_x_axis_is_one() throws Exception {
    given(vector1 = vector2(1, 0));
    when(vector1.length());
    thenReturned(1.0);
  }

  @Test
  public void length_of_unit_vector_along_minus_y_axis_is_one() throws Exception {
    given(vector1 = vector2(0, -1));
    when(vector1.length());
    thenReturned(1.0);
  }

  @Test
  public void length_of_vector_1_1_is_sqrt_2() throws Exception {
    given(vector1 = vector2(1, 1));
    when(vector1.length());
    thenReturned(sqrt(2));
  }

  @Test
  public void angle_0() throws Exception {
    when(vector2(1, 0).angle());
    thenReturned((0.0 / 4) * PI);
  }

  @Test
  public void angle_45() throws Exception {
    when(vector2(1, 1).angle());
    thenReturned((1.0 / 4) * PI);
  }

  @Test
  public void angle_90() throws Exception {
    when(vector2(0, 1).angle());
    thenReturned((2.0 / 4) * PI);
  }

  @Test
  public void angle_135() throws Exception {
    when(vector2(-1, 1).angle());
    thenReturned((3.0 / 4) * PI);
  }

  @Test
  public void angle_180() throws Exception {
    when(vector2(-1, 0).angle());
    thenReturned((4.0 / 4) * PI);
  }

  @Test
  public void angle_225() throws Exception {
    when(vector2(-1, -1).angle());
    thenReturned((5.0 / 4) * PI);
  }

  @Test
  public void angle_270() throws Exception {
    when(vector2(0, -1).angle());
    thenReturned((6.0 / 4) * PI);
  }

  @Test
  public void angle_315() throws Exception {
    when(vector2(1, -1).angle());
    thenReturned((7.0 / 4) * PI);
  }

  @Test
  public void negate() throws Exception {
    given(vector1 = vector2(1, -2));
    when(vector1.negate());
    thenReturned(vector2(-1, 2));
  }

  @Test
  public void negate_zero() throws Exception {
    given(vector1 = vector2(0, 0));
    when(vector1.negate());
    thenReturned(vector2(0, 0));
  }

  @Test
  public void vector_plus_vector() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(4, 8));
    when(vector1.plus(vector2));
    thenReturned(vector2(5, 10));
  }

  @Test
  public void vector_minus_vector() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(4, 8));
    when(vector1.minus(vector2));
    thenReturned(vector2(-3, -6));
  }

  @Test
  public void vector_multiplied_by_value() throws Exception {
    given(vector1 = vector2(0.5, -2));
    when(vector1.mul(3));
    thenReturned(vector2(1.5, -6));
  }

  @Test
  public void vector_divided_by_value() throws Exception {
    given(vector1 = vector2(6, -9));
    when(vector1.div(3));
    thenReturned(vector2(2, -3));
  }

  @Test
  public void dot_product() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(3, 5));
    when(vector1.dot(vector2));
    thenReturned(13.0);
  }

  @Test
  public void cross_product_on_itself_is_zero() throws Exception {
    given(vector1 = vector2(1, 2));
    when(vector1.cross(vector1));
    thenReturned(0.0);
  }

  @Test
  public void cross_product_on_negated_is_zero() throws Exception {
    given(vector1 = vector2(1, 2));
    when(vector1.cross(vector1.negate()));
    thenReturned(0.0);
  }

  @Test
  public void cross_product_of_perpendicular_unit_vectors_is_one() throws Exception {
    given(vector1 = vector2(1, 0));
    given(vector2 = vector2(0, 1));
    when(vector1.cross(vector2));
    thenReturned(1.0);
  }

  @Test
  public void normalized_unit_vector_is_equal_to_itself() throws Exception {
    given(vector1 = vector2(1, 0));
    when(vector1.normalize());
    thenReturned(vector1);
  }

  @Test
  public void normalized_vector_parallel_to_x_axis() throws Exception {
    given(vector1 = vector2(3, 0));
    when(vector1.normalize());
    thenReturned(vector2(1, 0));
  }

  @Test
  public void vector_is_equal_to_itself() throws Exception {
    given(vector1 = vector2(1, 2));
    when(vector1.equals(vector1));
    thenReturned(true);
  }

  @Test
  public void vectors_with_same_coordinates_are_equal() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(1, 2));
    when(vector1.equals(vector2));
    thenReturned(true);
  }

  @Test
  public void vector_with_different_x_is_not_equal() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(3, 2));
    when(vector1.equals(vector2));
    thenReturned(false);
  }

  @Test
  public void vector_with_different_y_is_not_equal() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(1, 3));
    when(vector1.equals(vector2));
    thenReturned(false);
  }

  @Test
  public void vector_is_not_equal_null() throws Exception {
    given(vector1 = vector2(1, 2));
    when(vector1.equals(null));
    thenReturned(false);
  }

  @Test
  public void equal_vectors_have_same_hashcode() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(1, 2));
    then(vector1.hashCode() == vector2.hashCode());
  }

  @Test
  public void not_equal_vectors_have_different_hashcode() throws Exception {
    given(vector1 = vector2(1, 2));
    given(vector2 = vector2(1, 3));
    then(vector1.hashCode() != vector2.hashCode());
  }

  @Test
  public void to_string() throws Exception {
    given(vector1 = vector2(1, 2));
    when(vector1.toString());
    thenReturned("v(1.0, 2.0)");
  }
}
