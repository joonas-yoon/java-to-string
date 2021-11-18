package com.joonas.test;

public class TestClassCanBeString extends TestClass {
  public TestClassCanBeString() {
    super();
  }
  
  public TestClassCanBeString(int a) {
    super(a);
  }

  public String toString() {
      return String.format("TestClassCanBeString[a=%d]", this.a);
  }
}