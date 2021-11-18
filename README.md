# Java object to string in many ways

This document describes how to use `Object.toString()` and how does it work in man cases.

# Environment
- openjdk 11.0.11 2021-04-20
- OpenJDK Runtime Environment (build 11.0.11+9-Ubuntu-0ubuntu2.18.04)
- OpenJDK 64-Bit Server VM (build 11.0.11+9-Ubuntu-0ubuntu2.18.04, mixed mode, sharing)

# Compile and Run

```bash
$ javac -sourcepath src -d build $(find src/ -name "*.java")
$ java -cp .:build:**/*.class com.joonas.Main
``````

## Built-in Classes

For print with built-in from `java.util` package, please see [JDK 11 documentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/package-summary.html)

If you want to add new class type to list in this document, please create Issue or Pull request.

**Define & declares**
```java
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
```

**Output:**
```
java.util.Date: Thu Nov 18 05:59:33 UTC 2021
java.util.GregorianCalendar: java.util.GregorianCalendar[time=1637215173302,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Etc/UTC",offset=0,dstSavings=0,useDaylight=false,transitions=0,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2021,MONTH=10,WEEK_OF_YEAR=47,WEEK_OF_MONTH=3,DAY_OF_MONTH=18,DAY_OF_YEAR=322,DAY_OF_WEEK=5,DAY_OF_WEEK_IN_MONTH=3,AM_PM=0,HOUR=5,HOUR_OF_DAY=5,MINUTE=59,SECOND=33,MILLISECOND=302,ZONE_OFFSET=0,DST_OFFSET=0]
java.util.BitSet: {10, 100}
java.util.Locale: ko_KR
java.util.Currency: KRW
java.util.Timer: java.util.Timer@79fc0f2f
```

## Built-in Array

**Define & declares**
```java
int[] arrayInt = {1, 2, 3, 4};
```

**Output:**
```
int[]: [I@50040f0c
```

**Define & declares**

```java
String[] names = {"apple", "banana", "cherry", "durian"};
```

**Output:**
```
[Ljava.lang.String;@2dda6444
```

**Define & declares**
```java
Object[] arrayObject = {
  (boolean)false,
  (int)1,
  (double)2.0,
  (String)"3-str",
};
```

**Output:**
```
[Ljava.lang.Object;@5e9f23b4
```

## Class without toString

**Define & declares**
```java
public class TestClass implements Comparable<TestClass> {
  public int a = 0;
  protected int b = 1;
  private int c = 2;
  int d = 3;
}
```

**Output:**
```
com.joonas.test.TestClass: com.joonas.test.TestClass@7106e68e
```

## Class with toString

**Define & declares**
```java
public class TestClassCanBeString extends TestClass {
  public String toString() {
      return String.format("TestClassCanBeString[a=%d]", this.a);
  }
}
```

**Output:**
```
com.joonas.test.TestClassCanBeString: TestClassCanBeString[a=0]
```

## Class in a Class

**Define & declares**
```java
public class TestClassInClass {
  private TestClass tc = new TestClass();
  private TestClass tc2 = new TestClassCanBeString();

  public String toString() {
    Object[] objs = { tc, tc2 };
    return Utils.toString(objs); // objs.map(obj -> "{class}: {obj.toString()}").join("\n")
  }
}
```

**Output:**
```
com.joonas.test.TestClassInClass:
  com.joonas.test.TestClass: com.joonas.test.TestClass@4d405ef7
  com.joonas.test.TestClassCanBeString: TestClassCanBeString[a=0]
```

## Objects in Collections

### String

**Define & declares**
```java
public class TestCollections {
  /* List */
  private ArrayList<Object> a1 = new ArrayList<Object>();
  private LinkedList<Object> a2 = new LinkedList<Object>();
  private Vector<Object> a3 = new Vector<Object>();
  /* Set */
  private HashSet<Object> b1 = new HashSet<Object>();
  private LinkedHashSet<Object> b2 = new LinkedHashSet<Object>();
  private TreeSet<Object> b3 = new TreeSet<Object>();
  /* Map */
  private HashMap<Object, Integer> c1 = new HashMap<Object, Integer>();
  private LinkedHashMap<Object, Integer> c2 = new LinkedHashMap<Object, Integer>();
  private Hashtable<Object, Integer> c3 = new Hashtable<Object, Integer>();
  private TreeMap<Object, Integer> c4 = new TreeMap<Object, Integer>();
}
```

Add strings `["apple", "banana", "cherry", "durian"]` to all collections. If it needs the key, put the size of its (Collection) for dummy.

**Output:**
```
com.joonas.test.TestCollections:
  java.util.ArrayList: [apple, banana, cherry, durian]
  java.util.LinkedList: [apple, banana, cherry, durian]
  java.util.Vector: [apple, banana, cherry, durian]
  java.util.HashSet: [banana, apple, cherry, durian]
  java.util.LinkedHashSet: [apple, banana, cherry, durian]
  java.util.TreeSet: [apple, banana, cherry, durian]
  java.util.HashMap: {banana=1, apple=0, cherry=2, durian=3}
  java.util.LinkedHashMap: {apple=0, banana=1, cherry=2, durian=3}
  java.util.Hashtable: {banana=1, apple=0, durian=3, cherry=2}
  java.util.TreeMap: {apple=0, banana=1, cherry=2, durian=3}
```

### new objects of TestClass

**Define & declares**
```java
TestCollections tc = new TestCollections();
tc.add(new TestClass());
tc.add(new TestClass());
tc.add(new TestClass());
```

**Output:**
```
com.joonas.test.TestCollections:
  java.util.ArrayList: [com.joonas.test.TestClass@254989ff, com.joonas.test.TestClass@5d099f62, com.joonas.test.TestClass@37f8bb67]
  java.util.LinkedList: [com.joonas.test.TestClass@254989ff, com.joonas.test.TestClass@5d099f62, com.joonas.test.TestClass@37f8bb67]
  java.util.Vector: [com.joonas.test.TestClass@254989ff, com.joonas.test.TestClass@5d099f62, com.joonas.test.TestClass@37f8bb67]
  java.util.HashSet: [com.joonas.test.TestClass@254989ff, com.joonas.test.TestClass@5d099f62, com.joonas.test.TestClass@37f8bb67]
  java.util.LinkedHashSet: [com.joonas.test.TestClass@254989ff, com.joonas.test.TestClass@5d099f62, com.joonas.test.TestClass@37f8bb67]
  java.util.TreeSet: [com.joonas.test.TestClass@254989ff]
  java.util.HashMap: {com.joonas.test.TestClass@254989ff=0, com.joonas.test.TestClass@5d099f62=1, com.joonas.test.TestClass@37f8bb67=2}
  java.util.LinkedHashMap: {com.joonas.test.TestClass@254989ff=0, com.joonas.test.TestClass@5d099f62=1, com.joonas.test.TestClass@37f8bb67=2}
  java.util.Hashtable: {com.joonas.test.TestClass@5d099f62=1, com.joonas.test.TestClass@37f8bb67=2, com.joonas.test.TestClass@254989ff=0}
  java.util.TreeMap: {com.joonas.test.TestClass@254989ff=1}
```

Both of `java.util.TreeSet` and `java.util.TreeMap` are not adding more items, even if it has different hash code.

> TODO:
> According to Java 11 Documentation, Sets contain no pair of elements `e1` and `e2` such that `e1.equals(e2)`.
> but the result of below is weired. It does not work as like document said.

### new objects of TestClass, with unique value

**Define & declares**
```java
TestCollections tc = new TestCollections();
tc.add(new TestClass(1));
tc.add(new TestClass(2));
tc.add(new TestClass(3));
```

**Output:**
```
com.joonas.test.TestCollections:
  java.util.ArrayList: [com.joonas.test.TestClass@17f052a3, com.joonas.test.TestClass@2e0fa5d3, com.joonas.test.TestClass@5010be6]
  java.util.LinkedList: [com.joonas.test.TestClass@17f052a3, com.joonas.test.TestClass@2e0fa5d3, com.joonas.test.TestClass@5010be6]
  java.util.Vector: [com.joonas.test.TestClass@17f052a3, com.joonas.test.TestClass@2e0fa5d3, com.joonas.test.TestClass@5010be6]
  java.util.HashSet: [com.joonas.test.TestClass@17f052a3, com.joonas.test.TestClass@5010be6, com.joonas.test.TestClass@2e0fa5d3]
  java.util.LinkedHashSet: [com.joonas.test.TestClass@17f052a3, com.joonas.test.TestClass@2e0fa5d3, com.joonas.test.TestClass@5010be6]
  java.util.TreeSet: [com.joonas.test.TestClass@17f052a3, com.joonas.test.TestClass@2e0fa5d3, com.joonas.test.TestClass@5010be6]
  java.util.HashMap: {com.joonas.test.TestClass@17f052a3=0, com.joonas.test.TestClass@5010be6=2, com.joonas.test.TestClass@2e0fa5d3=1}
  java.util.LinkedHashMap: {com.joonas.test.TestClass@17f052a3=0, com.joonas.test.TestClass@2e0fa5d3=1, com.joonas.test.TestClass@5010be6=2}
  java.util.Hashtable: {com.joonas.test.TestClass@2e0fa5d3=1, com.joonas.test.TestClass@5010be6=2, com.joonas.test.TestClass@17f052a3=0}
  java.util.TreeMap: {com.joonas.test.TestClass@17f052a3=0, com.joonas.test.TestClass@2e0fa5d3=1, com.joonas.test.TestClass@5010be6=2}
```

### exactly same instances of TestClassCanBeString

**Define & declares**
```java
TestCollections tc = new TestCollections();
TestClassCanBeString instance = new TestClassCanBeString();
tc.add(instance);
tc.add(instance);
tc.add(instance);
```

**Output:**
```
java.util.ArrayList: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.LinkedList: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.Vector: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.HashSet: [TestClassCanBeString[a=0]]
java.util.LinkedHashSet: [TestClassCanBeString[a=0]]
java.util.TreeSet: [TestClassCanBeString[a=0]]
java.util.HashMap: {TestClassCanBeString[a=0]=1}
java.util.LinkedHashMap: {TestClassCanBeString[a=0]=1}
java.util.Hashtable: {TestClassCanBeString[a=0]=1}
java.util.TreeMap: {TestClassCanBeString[a=0]=1}
```

Of course, all of `Set` and `Map` contain no duplicate instances. but `List` does.

### new TestClassCanBeString with all of same value

**Define & declares**
```java
TestCollections tc = new TestCollections();
tc.add(new TestClassCanBeString());
tc.add(new TestClassCanBeString());
tc.add(new TestClassCanBeString());
```

**Output:**
```
java.util.ArrayList: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.LinkedList: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.Vector: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.HashSet: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.LinkedHashSet: [TestClassCanBeString[a=0], TestClassCanBeString[a=0], TestClassCanBeString[a=0]]
java.util.TreeSet: [TestClassCanBeString[a=0]]
java.util.HashMap: {TestClassCanBeString[a=0]=0, TestClassCanBeString[a=0]=1, TestClassCanBeString[a=0]=2}
java.util.LinkedHashMap: {TestClassCanBeString[a=0]=0, TestClassCanBeString[a=0]=1, TestClassCanBeString[a=0]=2}
java.util.Hashtable: {TestClassCanBeString[a=0]=0, TestClassCanBeString[a=0]=1, TestClassCanBeString[a=0]=2}
java.util.TreeMap: {TestClassCanBeString[a=0]=1}
```

### new TestClassCanBeString with unique value

**Define & declares**
```java
TestCollections tc = new TestCollections();
tc.add(new TestClassCanBeString(1));
tc.add(new TestClassCanBeString(2));
tc.add(new TestClassCanBeString(3));
```

**Output:**
```
java.util.ArrayList: [TestClassCanBeString[a=1], TestClassCanBeString[a=2], TestClassCanBeString[a=3]]
java.util.LinkedList: [TestClassCanBeString[a=1], TestClassCanBeString[a=2], TestClassCanBeString[a=3]]
java.util.Vector: [TestClassCanBeString[a=1], TestClassCanBeString[a=2], TestClassCanBeString[a=3]]
java.util.HashSet: [TestClassCanBeString[a=1], TestClassCanBeString[a=3], TestClassCanBeString[a=2]]
java.util.LinkedHashSet: [TestClassCanBeString[a=1], TestClassCanBeString[a=2], TestClassCanBeString[a=3]]
java.util.TreeSet: [TestClassCanBeString[a=1], TestClassCanBeString[a=2], TestClassCanBeString[a=3]]
java.util.HashMap: {TestClassCanBeString[a=1]=0, TestClassCanBeString[a=3]=2, TestClassCanBeString[a=2]=1}
java.util.LinkedHashMap: {TestClassCanBeString[a=1]=0, TestClassCanBeString[a=2]=1, TestClassCanBeString[a=3]=2}
java.util.Hashtable: {TestClassCanBeString[a=2]=1, TestClassCanBeString[a=3]=2, TestClassCanBeString[a=1]=0}
java.util.TreeMap: {TestClassCanBeString[a=1]=0, TestClassCanBeString[a=2]=1, TestClassCanBeString[a=3]=2}
```

## Class using reflection

**Define & declares**
```java
new TestClassReflection();
```

**Output:**
```
com.joonas.test.TestClassReflection: TestClassReflection{protected int x = 0;private double y = 1.1;public static class java.lang.String z = z}
```

## Class using reflection in ArrayList

**Define & declares**
```java
ArrayList<Object> list = new ArrayList<>();
list.add(new TestClassReflection());
list.add(new TestClassReflection());
list.add(new TestClassReflection());
```

**Output:**
```
java.util.ArrayList: [TestClassReflection{protected int x = 0;private double y = 1.1;public static class java.lang.String z = z}, TestClassReflection{protected int x = 0;private double y = 1.1;public static class java.lang.String z = z}, TestClassReflection{protected int x = 0;private double y = 1.1;public static class java.lang.String z = z}]
```