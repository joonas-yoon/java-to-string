package com.joonas.test;

public class TestClass implements Comparable<TestClass> {
  public int a = 0;
  protected int b = 1;
  private int c = 2;
  int d = 3;

  public TestClass() {

  }

  public TestClass(int a) {
    this.a = a;
  }
  
  /* This is for Map, Set */
  @Override
  public int compareTo(TestClass o) {
      return this.a - o.a;
  }
}