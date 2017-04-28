package com.perunlabs.jsolid.util;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class Lists {
  public static <E> List<E> immutable(List<E> list) {
    return unmodifiableList(new ArrayList<>(list));
  }

  public static <E> List<E> reverse(List<E> list) {
    ArrayList<E> reversed = new ArrayList<>(list.size());
    for (int i = list.size() - 1; i >= 0; i--) {
      reversed.add(list.get(i));
    }
    return reversed;
  }
}
