/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.v3d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.mikosik.jsolid.d3.Vector3;

/**
 * Polyhedron.
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class Polyhedron {
  private final List<Vector3> points = new ArrayList<>();
  private final List<List<Integer>> faces = new ArrayList<>();

  /**
   * Constructor. Creates a polyhedron defined by a list of points and a list of
   * faces.
   *
   * @param points
   *          points ({@link Vector3} list)
   * @param faces
   *          list of faces (list of point index lists)
   */
  public Polyhedron(List<Vector3> points, List<List<Integer>> faces) {
    this.points.addAll(points);
    this.faces.addAll(faces);
  }

  /**
   * Constructor. Creates a polyhedron defined by a list of points and a list of
   * faces.
   *
   * @param points
   *          points ({@link Vector3} array)
   * @param faces
   *          list of faces (array of point index arrays)
   */
  public Polyhedron(Vector3[] points, Integer[][] faces) {
    this.points.addAll(Arrays.asList(points));

    for (Integer[] list : faces) {
      this.faces.add(Arrays.asList(list));
    }

  }

  public List<Polygon> toPolygons() {

    Function<Integer, Vector3> indexToPoint = (Integer i) -> {
      return points.get(i);
    };

    Function<List<Integer>, Polygon> faceListToPolygon = (List<Integer> faceList) -> {
      return Polygon.fromPoints(faceList.stream().map(indexToPoint).collect(Collectors.toList()));
    };

    return faces.stream().map(faceListToPolygon).collect(Collectors.toList());
  }
}
