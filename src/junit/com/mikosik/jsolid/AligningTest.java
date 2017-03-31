package com.mikosik.jsolid;

import static com.mikosik.jsolid.JSolid.alignOutside;
import static com.mikosik.jsolid.JSolid.cuboid;
import static com.mikosik.jsolid.JSolid.maxX;
import static com.mikosik.jsolid.JSolid.maxY;
import static com.mikosik.jsolid.JSolid.maxZ;
import static com.mikosik.jsolid.JSolid.minX;
import static com.mikosik.jsolid.JSolid.minY;
import static com.mikosik.jsolid.JSolid.minZ;
import static org.testory.Testory.given;
import static org.testory.Testory.thenReturned;
import static org.testory.Testory.when;

import org.junit.Test;

import com.mikosik.jsolid.d3.Solid;

public class AligningTest {
  private Solid solid;

  // align_???

  @Test
  public void align_minx() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), minX()));
    when(minX().valueIn(solid));
    thenReturned(-1.0);
  }

  @Test
  public void align_maxx() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), maxX()));
    when(maxX().valueIn(solid));
    thenReturned(1.0);
  }

  @Test
  public void align_miny() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), minY()));
    when(minY().valueIn(solid));
    thenReturned(-1.0);
  }

  @Test
  public void align_maxy() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), maxY()));
    when(maxY().valueIn(solid));
    thenReturned(1.0);
  }

  @Test
  public void align_minz() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), minZ()));
    when(minZ().valueIn(solid));
    thenReturned(-1.0);
  }

  @Test
  public void align_maxz() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), maxZ()));
    when(maxZ().valueIn(solid));
    thenReturned(1.0);
  }

  // alignOutside_???

  @Test
  public void alignOutside_minx() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(minX())));
    when(minX().valueIn(solid));
    thenReturned(-2.0);
  }

  @Test
  public void alignOutside_maxx() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(maxX())));
    when(maxX().valueIn(solid));
    thenReturned(2.0);
  }

  @Test
  public void alignOutside_miny() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(minY())));
    when(minY().valueIn(solid));
    thenReturned(-2.0);
  }

  @Test
  public void alignOutside_maxy() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(maxY())));
    when(maxY().valueIn(solid));
    thenReturned(2.0);
  }

  @Test
  public void alignOutside_minz() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(minZ())));
    when(minZ().valueIn(solid));
    thenReturned(-2.0);
  }

  @Test
  public void alignOutside_maxz() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(maxZ())));
    when(maxZ().valueIn(solid));
    thenReturned(2.0);
  }

  // align_???_with_margin

  @Test
  public void align_minx_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), minX(3)));
    when(minX().valueIn(solid));
    thenReturned(-4.0);
  }

  @Test
  public void align_maxx_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), maxX(3)));
    when(maxX().valueIn(solid));
    thenReturned(4.0);
  }

  @Test
  public void align_miny_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), minY(3)));
    when(minY().valueIn(solid));
    thenReturned(-4.0);
  }

  @Test
  public void align_maxy_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), maxY(3)));
    when(maxY().valueIn(solid));
    thenReturned(4.0);
  }

  @Test
  public void align_minz_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), minZ(3)));
    when(minZ().valueIn(solid));
    thenReturned(-4.0);
  }

  @Test
  public void align_maxz_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), maxZ(3)));
    when(maxZ().valueIn(solid));
    thenReturned(4.0);
  }

  // alignOutside_???_with_margin

  @Test
  public void alignOutside_minx_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(minX(3))));
    when(minX().valueIn(solid));
    thenReturned(-5.0);
  }

  @Test
  public void alignOutside_maxx_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(maxX(3))));
    when(maxX().valueIn(solid));
    thenReturned(5.0);
  }

  @Test
  public void alignOutside_miny_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(minY(3))));
    when(minY().valueIn(solid));
    thenReturned(-5.0);
  }

  @Test
  public void alignOutside_maxy_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(maxY(3))));
    when(maxY().valueIn(solid));
    thenReturned(5.0);
  }

  @Test
  public void alignOutside_minz_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(minZ(3))));
    when(minZ().valueIn(solid));
    thenReturned(-5.0);
  }

  @Test
  public void alignOutside_maxz_with_margin() throws Exception {
    given(solid = cuboid(2, 2, 2));
    given(solid = solid.add(cuboid(1, 1, 1), alignOutside(maxZ(3))));
    when(maxZ().valueIn(solid));
    thenReturned(5.0);
  }
}
