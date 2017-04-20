package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.edge;
import static com.mikosik.jsolid.JSolid.v;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.testory.Testory.given;
import static org.testory.Testory.then;
import static org.testory.Testory.thenEqual;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

public class EdgeTest {
  private Edge edge;
  private Edge edge2;

  @Test
  public void flip() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    when(() -> edge.flip());
    thenReturned(edge(v(4, 5, 6), v(1, 2, 3)));
  }

  @Test
  public void is_equal_to_edge_with_same_points() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    given(edge2 = edge(v(1, 2, 3), v(4, 5, 6)));
    thenEqual(edge, edge2);
  }

  @Test
  public void is_not_equal_to_edge_with_points_reordered() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    given(edge2 = edge(v(4, 5, 6), v(1, 2, 3)));
    then(edge, not(equalTo(edge2)));
  }

  @Test
  public void is_not_equal_to_edge_with_different_point_a() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    given(edge2 = edge(v(0, 2, 3), v(4, 5, 6)));
    then(edge, not(equalTo(edge2)));
  }

  @Test
  public void is_not_equal_to_edge_with_different_point_b() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    given(edge2 = edge(v(1, 2, 3), v(0, 5, 6)));
    then(edge, not(equalTo(edge2)));
  }

  @Test
  public void equal_edges_have_same_hashcode() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    given(edge2 = edge(v(1, 2, 3), v(4, 5, 6)));
    thenEqual(edge.hashCode(), edge2.hashCode());
  }

  @Test
  public void different_edges_have_different_hashcodes() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    given(edge2 = edge(v(0, 2, 3), v(4, 5, 6)));
    then(edge.hashCode(), not(equalTo(edge2.hashCode())));
  }

  @Test
  public void to_string() throws Exception {
    given(edge = edge(v(1, 2, 3), v(4, 5, 6)));
    when(() -> edge.toString());
    thenReturned("edge(v(1.0, 2.0, 3.0), v(4.0, 5.0, 6.0))");
  }
}
