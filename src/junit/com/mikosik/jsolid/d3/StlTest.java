package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.x;
import static com.mikosik.jsolid.JSolid.y;
import static com.mikosik.jsolid.JSolid.z;
import static java.util.Arrays.asList;
import static org.testory.Testory.given;
import static org.testory.Testory.mock;
import static org.testory.Testory.thenEqual;
import static org.testory.Testory.when;
import static org.testory.Testory.willReturn;

import java.io.StringWriter;

import org.junit.Test;

import eu.mihosoft.vrl.v3d.Polygon;

public class StlTest {
  private StringWriter writer;
  private Solid solid;
  private Polygon side;

  @Test
  public void toStl() throws Exception {
    given(writer = new StringWriter());
    given(solid = mock(Solid.class));
    given(side = new Polygon(x(), y(), z(), z().neg()));
    given(willReturn(asList(side)), solid).sides();
    when(() -> Stl.toStl(solid, writer));
    thenEqual(writer.toString(),
        "solid \n"
            + "facet normal 0.5773502691896258 0.5773502691896258 0.5773502691896258\n"
            + "  outer loop\n"
            + "    vertex 1.0 0.0 0.0\n"
            + "    vertex 0.0 1.0 0.0\n"
            + "    vertex 0.0 0.0 1.0\n"
            + "  endloop\n"
            + "endfacet\n"
            + "facet normal 0.5773502691896258 0.5773502691896258 0.5773502691896258\n"
            + "  outer loop\n"
            + "    vertex 1.0 0.0 0.0\n"
            + "    vertex 0.0 0.0 1.0\n"
            + "    vertex -0.0 -0.0 -1.0\n"
            + "  endloop\n"
            + "endfacet\n"
            + "endsolid\n");
  }
}
