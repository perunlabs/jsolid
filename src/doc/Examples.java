import static com.perunlabs.jsolid.JSolid.align;
import static com.perunlabs.jsolid.JSolid.alignOutside;
import static com.perunlabs.jsolid.JSolid.cuboid;
import static com.perunlabs.jsolid.JSolid.cylinder;
import static com.perunlabs.jsolid.JSolid.degrees;
import static com.perunlabs.jsolid.JSolid.maxX;
import static com.perunlabs.jsolid.JSolid.maxZ;
import static com.perunlabs.jsolid.JSolid.minY;
import static com.perunlabs.jsolid.JSolid.prism;
import static com.perunlabs.jsolid.JSolid.regularPolygon;
import static com.perunlabs.jsolid.JSolid.v;
import static com.perunlabs.jsolid.JSolid.vx;
import static com.perunlabs.jsolid.JSolid.y;
import static com.perunlabs.jsolid.JSolid.z;

import com.perunlabs.jsolid.JSolid;
import com.perunlabs.jsolid.d1.Angle;
import com.perunlabs.jsolid.d3.Solid;

public class Examples {

  public static Solid simpleCuboid() {
    return cuboid(2, 4, 8);
  }

  public static Solid cuboidWithRoundedCorners() {
    return cuboid(5, 5, 5)
        .cornerR(z(), 1);
  }

  public static Solid simpleCylinder() {
    return cylinder(5, 10);
  }

  public static Solid cylinderWithManySegments() {
    return cylinder(10, 2)
        .addSegment(5, 10)
        .addFunnel(3, 2)
        .addSegment(4);
  }

  public static Solid rotated() {
    return cylinder(2, 10)
        .rotate(y(), degrees(45));
  }

  public static Solid addingSolids() {
    return cylinder(2, 10)
        .add(cuboid(10, 2, 5));
  }

  public static Solid subtractingSolids() {
    return cylinder(2, 10)
        .sub(cuboid(10, 2, 5));
  }

  public static Solid intersectingSolids() {
    return cylinder(2, 10)
        .intersect(cuboid(10, 2, 5));
  }

  public static Solid addingWithShift() {
    return cylinder(3, 1)
        .add(cuboid(6, 6, 1).moveBy(vx(5)));
  }

  public static Solid subtractingWithAlignment() {
    return cuboid(4, 4, 4)
        .sub(cylinder(1, 1), align(maxZ()));
  }

  public static Solid subtractingWithTwoAlignments() {
    return cuboid(4, 4, 4)
        .sub(cuboid(1, 1, 4), align(maxX()), align(minY()));
  }

  public static Solid addingWithOutsideAlignment() {
    return cuboid(4, 4, 4)
        .add(cylinder(2, 4), alignOutside(maxZ()));
  }

  public static Solid addingWithOutsideAlignmentAndMargin() {
    return cuboid(4, 4, 4)
        .add(cylinder(2, 1), alignOutside(maxZ(), 2));
  }

  public static Solid convexHull() {
    return cuboid(4, 4, 1)
        .add(cylinder(1, 1).moveBy(vx(5)))
        .convexHull();
  }

  public static Solid prismWithRegularPolygonAsBase() {
    return prism(regularPolygon(4, 8), 4);
  }

  public static Solid clonedStepFormingStairs() {
    return cuboid(10, 4, 2)
        .clone(30, (i, s) -> s.moveBy(v(30, 0, i * 2)).rotate(z(), degrees(i * 6)));
  }
}
