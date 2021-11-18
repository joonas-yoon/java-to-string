package com.joonas.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {
  public static String toString(Object object) {
    return object.getClass().getCanonicalName() + ": " + object.toString();
  }
  public static String toString(Object[] objectArray) {
    return Arrays.stream(objectArray).map(collection -> toString(collection))
      .collect(Collectors.joining("\n"));
  }
}