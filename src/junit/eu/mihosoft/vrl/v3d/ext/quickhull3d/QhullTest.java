package eu.mihosoft.vrl.v3d.ext.quickhull3d;

import static org.junit.Assert.fail;

import org.junit.Test;

public class QhullTest {

  @Test
  public void test_qhull() throws Exception {
    QuickHull3D hull = new QuickHull3D();
    QuickHull3DTest tester = new QuickHull3DTest();

    hull = new QuickHull3D();

    for (int i = 0; i < 100; i++) {
      double[] pnts = tester.randomCubedPoints(100, 1.0, 0.5);

      // hull = new QuickHull3D ();
      hull.build(pnts, pnts.length / 3);
      hull.triangulate();

      if (!hull.check(System.out)) {
        fail("failed for QuickHull3D triangulated");
      }

      // hull = new QuickHull3D ();
      hull.build(pnts, pnts.length / 3);

      if (!hull.check(System.out)) {
        fail("failed for QuickHull3D regular");
      }
    }
  }
}
