package com.mikosik.jsolid.d3;

import static com.mikosik.jsolid.JSolid.v;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Vector3Matchers {
  public static Matcher<Vector3> closeTo(final Vector3 expected) {
    double delta = 1e-15;
    return new TypeSafeMatcher<Vector3>() {
      @Override
      public boolean matchesSafely(Vector3 value) {
        Vector3 actualDelta = actualDelta(value);
        return actualDelta.x <= 0.0
            && actualDelta.y <= 0.0
            && actualDelta.z <= 0.0;
      }

      @Override
      public void describeMismatchSafely(Vector3 v, Description mismatchDescription) {
        mismatchDescription.appendValue(v)
            .appendText(" differed by ")
            .appendValue(actualDelta(v));
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("a vector value within ")
            .appendValue(delta)
            .appendText(" of ")
            .appendValue(expected);
      }

      private Vector3 actualDelta(Vector3 value) {
        return value.dif(expected).sub(v(delta, delta, delta));
      }
    };
  }
}
