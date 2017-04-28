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
