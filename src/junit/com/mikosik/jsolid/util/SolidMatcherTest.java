package com.mikosik.jsolid.util;

import static com.mikosik.jsolid.JSolid.v;
import static com.mikosik.jsolid.util.SolidMatcher.matchesSolid;
import static java.util.Arrays.asList;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

import com.mikosik.jsolid.d3.Solid;
import com.mikosik.jsolid.d3.Vector3;
import com.mikosik.jsolid.d3.op.AbstractSolid;

import eu.mihosoft.vrl.v3d.Polygon;

public class SolidMatcherTest {
  private Matcher<Solid> matcher;

  @Test
  public void matches() throws Exception {
    given(matcher = matchesSolid(
        v(0, 0, 0),
        v(1, 0, 0),
        v(0, 1, 0)));
    when(matcher.matches(solid(
        poly(v(0, 0, 0), v(1, 0, 0), v(0, 1, 0)),
        poly(v(0, 0, 0), v(0, 1, 0), v(1, 0, 0)))));
    thenReturned(true);
  }

  @Test
  public void no_match_when_vertex_is_missing() throws Exception {
    given(matcher = matchesSolid(
        v(0, 0, 0),
        v(1, 0, 0),
        v(0, 1, 0),
        v(33, 33, 33)));
    when(matcher.matches(solid(
        poly(v(0, 0, 0), v(1, 0, 0), v(0, 1, 0)),
        poly(v(0, 0, 0), v(0, 1, 0), v(1, 0, 0)))));
    thenReturned(false);
  }

  @Test
  public void no_match_when_extra_vertex_is_present() throws Exception {
    given(matcher = matchesSolid(
        v(0, 0, 0),
        v(1, 0, 0),
        v(0, 1, 0)));
    when(matcher.matches(solid(
        poly(v(0, 0, 0), v(1, 0, 0), v(1, 1, 0), v(0, 1, 0)),
        poly(v(0, 0, 0), v(0, 1, 0), v(1, 1, 0), v(1, 0, 0)))));
    thenReturned(false);
  }

  @Test
  public void no_match_when_side_has_doubled_vertex() throws Exception {
    given(matcher = matchesSolid(
        v(0, 0, 0),
        v(1, 0, 0),
        v(0, 1, 0)));
    when(matcher.matches(solid(
        poly(v(0, 0, 0), v(1, 0, 0), v(0, 1, 0), v(0, 0, 0)),
        poly(v(0, 0, 0), v(0, 1, 0), v(1, 0, 0)))));
    thenReturned(false);
  }

  @Test
  public void no_match_when_edge_doesnt_have_flipped_partner() throws Exception {
    given(matcher = matchesSolid(
        v(0, 0, 0),
        v(1, 0, 0),
        v(0, 1, 0),
        v(1, 1, 0)));
    when(matcher.matches(solid(
        poly(v(0, 0, 0), v(1, 0, 0), v(1, 1, 0), v(0, 1, 0)),
        poly(v(0, 0, 0), v(0, 1, 0), v(1, 0, 0)))));
    thenReturned(false);
  }

  private Polygon poly(Vector3... vertexes) {
    return new Polygon(vertexes);
  }

  private static Solid solid(Polygon... polygons) {
    return new AbstractSolid() {

      protected List<Polygon> calculateSides() {
        return asList(polygons);
      }
    };
  }
}
