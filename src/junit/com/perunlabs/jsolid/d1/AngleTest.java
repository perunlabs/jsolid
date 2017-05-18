package com.perunlabs.jsolid.d1;

import static com.perunlabs.jsolid.d1.Angle.degrees;
import static com.perunlabs.jsolid.d1.Angle.perigons;
import static com.perunlabs.jsolid.d1.Angle.radians;
import static java.lang.Math.PI;
import static org.testory.Testory.given;
import static org.testory.Testory.thenEqual;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class AngleTest {
  private Angle angle;
  private Angle angle2;

  @Test
  public void one_perigon_equals_360_degrees() throws Exception {
    given(angle = perigons(1));
    when(() -> angle.degrees());
    thenReturned(360.0);
  }

  @Test
  public void one_perigon_equals_2_pi_radians() throws Exception {
    given(angle = perigons(1));
    when(() -> angle.radians());
    thenReturned(2 * PI);
  }

  @Test
  public void _360_degrees_equals_one_perigon() throws Exception {
    given(angle = degrees(360));
    when(() -> angle.perigon());
    thenReturned(1.0);
  }

  @Test
  public void _360_degrees_equals_2_pi_radians() throws Exception {
    given(angle = degrees(360));
    when(() -> angle.radians());
    thenReturned(2 * PI);
  }

  @Test
  public void _2_PI_radians_equals_1_perigon() throws Exception {
    given(angle = radians(2 * PI));
    when(() -> angle.perigon());
    thenReturned(1.0);
  }

  @Test
  public void _2_PI_radians_equals_360_degrees() throws Exception {
    given(angle = radians(2 * PI));
    when(() -> angle.degrees());
    thenReturned(360.0);
  }

  @Test
  public void equal_angles() throws Exception {
    given(angle = perigons(3));
    given(angle2 = perigons(3));
    thenEqual(angle, angle2);
  }

  @Test
  public void not_equal_angles() throws Exception {
    given(angle = perigons(3));
    given(angle2 = perigons(2));
    when(angle.equals(angle2));
    thenReturned(false);
  }

  @Test
  public void equal_angles_have_equal_hashes() throws Exception {
    given(angle = perigons(3));
    given(angle2 = perigons(3));
    thenEqual(angle.hashCode(), angle2.hashCode());
  }

  @Test
  public void to_string() throws Exception {
    given(angle = perigons(12.3));
    when(() -> angle.toString());
    thenReturned("12.3 perigons");
  }
}
