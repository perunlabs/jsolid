package com.mikosik.jsolid.util;

import static com.mikosik.jsolid.util.Lists.immutable;
import static java.util.Arrays.asList;
import static org.testory.Testory.given;
import static org.testory.Testory.thenEqual;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import java.util.List;

import org.junit.Test;

public class ListsTest {
  private List<Integer> list;
  private List<Integer> list2;

  @Test
  public void immutable_does_defensive_copy_of_argument() throws Exception {
    given(list = asList(1, 2, 3));
    given(list2 = Lists.immutable(list));
    when(list).set(0, 0);
    thenEqual(list2, asList(1, 2, 3));
  }

  @Test
  public void immutable_creates_equal_copy() throws Exception {
    given(list = asList(1, 2, 3));
    when(immutable(list));
    thenReturned(asList(1, 2, 3));
  }

  @Test
  public void reverse_reverses_elements() throws Exception {
    given(list = asList(1, 2, 3));
    when(Lists.reverse(list));
    thenReturned(asList(3, 2, 1));
  }
}
