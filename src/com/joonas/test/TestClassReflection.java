package com.joonas.test;

import com.joonas.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.lang.reflect.*;

public class TestClassReflection extends TestClass {
  protected int x = 0;
  private double y = 1.1;
  static public String z = "z";

  public String toString() {
    ArrayList<String> variables = new ArrayList<>();
    try {
      Class cls = Class.forName(this.getClass().getName());
      for (Field field : cls.getDeclaredFields()) {
        String info =
          Modifier.toString(field.getModifiers()) + " " +
          field.getType() + " " +
          field.getName() + " = " +
          field.get(this);
        variables.add(info);
      }
    } catch (ClassNotFoundException e) {
    } catch (IllegalAccessException e) {
    }
    return "TestClassReflection{"
      + variables.stream().collect(Collectors.joining(";"))
      + "}";
  }
}