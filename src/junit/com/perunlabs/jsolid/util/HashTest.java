package com.perunlabs.jsolid.util;

import static org.hamcrest.Matchers.not;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class HashTest {
  @Test
  public void equal_values_have_equal_hash() throws Exception {
    when(Hash.hash(33));
    thenReturned(Hash.hash(33));
  }

  @Test
  public void different_values_have_equal_hash() throws Exception {
    when(Hash.hash(33));
    thenReturned(not(Hash.hash(34)));
  }

  @Test
  public void zero_and_zero_have_equal_hash() throws Exception {
    when(Hash.hash(0.0));
    thenReturned(Hash.hash(0.0));
  }

  @Test
  public void zero_and_minus_zero_have_equal_hash() throws Exception {
    when(Hash.hash(0.0));
    thenReturned(Hash.hash(-0.0));
  }

  @Test
  public void zero_and_zero_have_equal_hash_array_version() throws Exception {
    when(Hash.hash(0.0));
    thenReturned(Hash.hash(0.0));
  }

  @Test
  public void zero_and_minus_zero_have_equal_hash_array_version() throws Exception {
    when(Hash.hash(0.0, 1.0));
    thenReturned(Hash.hash(-0.0, 1.0));
  }
}
