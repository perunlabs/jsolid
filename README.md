jsolid is a java library for
[Constructive Solid Geometry](https://en.wikipedia.org/wiki/Constructive_solid_geometry)
that internally uses [JCSG library](https://github.com/miho/JCSG) which uses
[binary space partitioning algorithm](https://en.wikipedia.org/wiki/Binary_space_partitioning).
It's great for programmatically generating STL shapes for 3d printer.

### Tutorial

To use jsolid you need download latest [release jar](https://github.com/perunlabs/jsolid/releases).
Whole API is available via static methods of
[JSolid](https://github.com/perunlabs/jsolid/blob/master/src/java/com/perunlabs/jsolid/JSolid.java)
class so all you need is to import it by adding following line
(which is omitted for simplicity in all following examples):

```
import static com.perunlabs.jsolid.JSolid.*;
```

All curved (rounded) edges are approximated with finite precision,
which is configured by specifying maximal distance allowed between required curve
and closest point of approximated solid.

```
config().setCircleToPolygonPrecision(0.1);
```

### Examples
simple cuboid ([preview](./doc/simpleCuboid.stl))

```
cuboid(2, 4, 8);
```

cuboid with rounded corners ([preview](./doc/cuboidWithRoundedCorners.stl))

```
cuboid(5, 5, 5)
  .cornerR(z(), 1);
```

simple cylinder ([preview](./doc/simpleCylinder.stl))

```
cylinder(5, 10);
```

cylinder with many segments ([preview](./doc/cylinderWithManySegments.stl))

```
cylinder(10, 2)
  .addSegment(5, 10)
  .addFunnel(3, 2)
  .addSegment(4);
```

rotated ([preview](./doc/rotated.stl))

```
cylinder(2, 10)
  .rotate(y(), 45);
```

adding solids ([preview](./doc/addingSolids.stl))

```
cylinder(2, 10)
  .add(cuboid(10, 2, 5));
```

subtracting solids ([preview](./doc/subtractingSolids.stl))

```
cylinder(2, 10)
  .sub(cuboid(10, 2, 5));
```

intersecting solids ([preview](./doc/intersectingSolids.stl))

```
cylinder(2, 10)
  .intersect(cuboid(10, 2, 5));
```

adding with shift ([preview](./doc/addingWithShift.stl))

```
cylinder(3, 1)
  .add(cuboid(6, 6, 1).moveBy(vx(5)));
```

subtracting with alignment ([preview](./doc/subtractingWithAlignment.stl))

```
cuboid(4, 4, 4)
  .sub(cylinder(1, 1), align(maxZ()));
```

subtracting with two alignments ([preview](./doc/subtractingWithTwoAlignments.stl))

```
cuboid(4, 4, 4)
  .sub(cuboid(1, 1, 4), align(maxX()), align(minY()));
```

adding with outside alignment ([preview](./doc/addingWithOutsideAlignment.stl))

```
cuboid(4, 4, 4)
  .add(cylinder(2, 4), alignOutside(maxZ()));
```

adding with outside alignment and margin ([preview](./doc/addingWithOutsideAlignmentAndMargin.stl))

```
cuboid(4, 4, 4)
  .add(cylinder(2, 1), alignOutside(maxZ(), 2));
```

convex hull ([preview](./doc/convexHull.stl))

```
cuboid(4, 4, 1)
  .add(cylinder(1, 1).moveBy(vx(5)))
  .convexHull();
```

prism with regular polygon as base ([preview](./doc/prismWithRegularPolygonAsBase.stl))

```
prism(regularPolygon(4, 8), 4);
```

