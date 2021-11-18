package com.joonas;

import java.util.*;
import com.joonas.test.*;
import com.joonas.util.*;

class Main {
  static final String[] names = {"apple", "banana", "cherry", "durian"};

  /**
  * Print as String with java built-in classes
  * @link https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/package-summary.html
  **/
  static void testBuiltInClasses() {
    System.out.println("## Built-in Classes");
    
    BitSet bitset = new BitSet();
    bitset.set(10);
    bitset.set(100);

    Object[] builtIns = {
      new Date(),
      Calendar.getInstance(),
      bitset,
      Locale.KOREA,
      Currency.getInstance(Locale.KOREA),
      new Timer()
    };
    System.out.println(Utils.toString(builtIns));
    System.out.println("\n");
  }

  static void testBuiltInArray() {
    System.out.println("## Built-in Array");
    Object[] arrayObject = {
      (boolean)false,
      (int)1,
      (double)2.0,
      (String)"3-str",
    };
    int[] arrayInt = {1, 2, 3, 4};
    System.out.println(String.valueOf(arrayInt));
    System.out.println(String.valueOf(names));
    System.out.println(String.valueOf(arrayObject));
    System.out.println("\n");
  }
  
  static void testClass() {
    System.out.println("## Class without toString");
    System.out.println(Utils.toString(new TestClass()));
    System.out.println("\n");

    System.out.println("## Class with toString");
    System.out.println(Utils.toString(new TestClassCanBeString()));
    System.out.println("\n");
    
    System.out.println("## Class in a Class");
    System.out.println(Utils.toString(new TestClassInClass()));
    System.out.println("\n");
  }

  static void testWithCollections() {
    System.out.println("## Objects in Collections");
    System.out.println("### String");
    TestCollections tc = new TestCollections();
    for (String name : names) {
      tc.add(name);
    }
    System.out.println(Utils.toString(tc));
    System.out.println("\n");
    
    System.out.println("### new objects of TestClass");
    tc.clear();
    tc.add(new TestClass());
    tc.add(new TestClass());
    tc.add(new TestClass());
    System.out.println(Utils.toString(tc));
    System.out.println("\n");
    
    System.out.println("### new objects of TestClass, with unique value");
    tc.clear();
    tc.add(new TestClass(1));
    tc.add(new TestClass(2));
    tc.add(new TestClass(3));
    System.out.println(Utils.toString(tc));
    System.out.println("\n");
  
    System.out.println("### exactly same instances of TestClassCanBeString");
    TestClassCanBeString instance = new TestClassCanBeString();
    tc.clear();
    tc.add(instance);
    tc.add(instance);
    tc.add(instance);
    System.out.println(tc);
    System.out.println("\n");

    System.out.println("### new TestClassCanBeString with all of same value");
    tc.clear();
    tc.add(new TestClassCanBeString());
    tc.add(new TestClassCanBeString());
    tc.add(new TestClassCanBeString());
    System.out.println(tc);
    System.out.println("\n");
    
    System.out.println("### new TestClassCanBeString with unique value");
    tc.clear();
    tc.add(new TestClassCanBeString(1));
    tc.add(new TestClassCanBeString(2));
    tc.add(new TestClassCanBeString(3));
    System.out.println(tc);
    System.out.println("\n");
  }

  static void testWithReflection() {
    System.out.println("## Class using reflection");
    System.out.println(Utils.toString(new TestClassReflection()));
    System.out.println("\n");
    
    System.out.println("## Class using reflection in ArrayList");
    ArrayList<Object> list = new ArrayList<>();
    list.add(new TestClassReflection());
    list.add(new TestClassReflection());
    list.add(new TestClassReflection());
    System.out.println(Utils.toString(list));
    System.out.println("\n");
  }

  public static void main(String[] args) {
    testBuiltInClasses();
    testBuiltInArray();
    testClass();
    testWithCollections();
    testWithReflection();
  }
}