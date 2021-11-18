package com.joonas.test;

import com.joonas.util.Utils;

public class TestClassInClass {
  private TestClass tc = new TestClass();
  private TestClass tc2 = new TestClassCanBeString();

  public String toString() {
    Object[] objs = { tc, tc2 };
    return Utils.toString(objs);
  }
}