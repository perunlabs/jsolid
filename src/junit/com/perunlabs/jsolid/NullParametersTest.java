package com.perunlabs.jsolid;

import static com.perunlabs.jsolid.JSolid.convexPolygon;
import static com.perunlabs.jsolid.JSolid.max;
import static com.perunlabs.jsolid.JSolid.maxX;
import static com.perunlabs.jsolid.JSolid.range;
import static com.perunlabs.jsolid.JSolid.v;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.perunlabs.jsolid.d1.Anchor1;
import com.perunlabs.jsolid.d1.Range;
import com.perunlabs.jsolid.d2.Polygon;
import com.perunlabs.jsolid.d2.Vector2;
import com.perunlabs.jsolid.d3.Anchor3;
import com.perunlabs.jsolid.d3.Vector3;

public class NullParametersTest {
  private static final Map<Class<?>, Object> DEFAULTS = defaultsMap();

  @Test
  public void jsolid_api_forbids_null_parameters() throws Exception {
    Method[] methods = JSolid.class.getDeclaredMethods();
    for (Method method : methods) {
      Class<?>[] types = method.getParameterTypes();
      for (int i = 0; i < types.length; i++) {
        if (!types[i].isPrimitive()) {
          Object[] args = createArguments(types, i);
          try {
            method.invoke(null, args);
            failWithMessage(method, i);
          } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof NullPointerException)) {
              failWithMessage(method, i);
            }
          }
        }
      }
    }
  }

  private void failWithMessage(Method method, int i) {
    fail("Method '" + method.getName() + "' should throw NullPointerException when argument "
        + i + " is null.");
  }

  private static Object[] createArguments(Class<?>[] types, int nullIndex) {
    Object[] objects = new Object[types.length];
    for (int i = 0; i < types.length; i++) {
      if (i == nullIndex) {
        objects[i] = null;
      } else {
        objects[i] = defaultValueFor(types[i]);
      }
    }
    return objects;
  }

  private static Object defaultValueFor(Class<?> type) {
    Object result = DEFAULTS.get(type);
    if (result == null) {
      fail("Unknown type " + type.getName());
    }
    return result;
  }

  private static Map<Class<?>, Object> defaultsMap() {
    Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
    map.put(double.class, 1d);
    map.put(float.class, 1f);
    map.put(boolean.class, false);
    map.put(byte.class, (byte) 1);
    map.put(short.class, (short) 1);
    map.put(int.class, 1);
    map.put(long.class, 1L);
    map.put(char.class, '\0');
    map.put(Range.class, range(1));
    map.put(Anchor1.class, max());
    map.put(Anchor3.class, maxX());
    map.put(Vector2.class, v(0, 0));
    map.put(Vector3.class, v(0, 0, 0));
    map.put(Polygon.class, convexPolygon(v(0, 0), v(1, 0), v(0, 1)));
    map.put(List.class, new ArrayList<>());
    return Collections.unmodifiableMap(map);
  }
}
