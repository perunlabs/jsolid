package com.mikosik.jsolid.util;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.mikosik.jsolid.d3.Edge;
import com.mikosik.jsolid.d3.Solid;
import com.mikosik.jsolid.d3.Vector3;

public class SolidMatcher extends TypeSafeMatcher<Solid> {
  private final Set<Vector3> vertexes;

  public static Matcher<Solid> matchesSolid(Vector3... vertexes) {
    return new SolidMatcher(vertexes);
  }

  private SolidMatcher(Vector3[] vertexes) {
    this.vertexes = toSet(vertexes);
  }

  private static HashSet<Vector3> toSet(Vector3[] vertexes) {
    HashSet<Vector3> set = new HashSet<>();
    Collections.addAll(set, vertexes);
    return set;
  }

  public void describeTo(Description description) {
    description.appendText("is equal to given solid.");
  }

  protected boolean matchesSafely(Solid solid) {
    if (!vertexesMatch(solid)) {
      return false;
    }
    if (hasSideWithDoubleVertex(solid)) {
      return false;
    }
    if (!eachEdgeHasFlippedPartner(solid)) {
      return false;
    }
    return true;
  }

  private boolean vertexesMatch(Solid solid) {
    Set<Vector3> actualVertexes = solid.sides().stream()
        .flatMap(s -> s.vertices.stream())
        .collect(Collectors.toSet());
    return actualVertexes.equals(vertexes);
  }

  private static boolean hasSideWithDoubleVertex(Solid solid) {
    return solid.sides().stream()
        .anyMatch(side -> hasDoubledVertex(side.vertices));
  }

  private static boolean hasDoubledVertex(List<Vector3> vertexes) {
    for (int i = 0; i < vertexes.size(); i++) {
      for (int j = i + 1; j < vertexes.size(); j++) {
        if (vertexes.get(i).equals(vertexes.get(j))) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean eachEdgeHasFlippedPartner(Solid solid) {
    List<Edge> edges = solid.sides().stream()
        .flatMap(s -> s.edges().stream())
        .collect(toList());
    Map<Edge, Long> counts = edges.stream()
        .collect(groupingBy(identity(), counting()));
    for (Edge edge : edges) {
      if (counts.get(edge) != 1) {
        System.out.println(counts.get(edge));
        return false;
      }
      Long flippedCount = counts.get(edge.flip());
      if (flippedCount == null || flippedCount != 1) {
        return false;
      }
    }
    return true;
  }
}
