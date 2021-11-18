package com.joonas.test;

import java.util.*;
import com.joonas.util.*;

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

  public TestCollections() {}

  public void add(Object object) {
      /* List */
      a1.add(object);
      a2.add(object);
      a3.add(object);
      /* Set */
      b1.add(object);
      b2.add(object);
      b3.add(object);
      /* Map */
      c1.put(object, c1.size());
      c2.put(object, c2.size());
      c3.put(object, c3.size());
      c4.put(object, c4.size());
  }

  public void clear() {
      /* List */
      a1.clear();
      a2.clear();
      a3.clear();
      /* Set */
      b1.clear();
      b2.clear();
      b3.clear();
      /* Map */
      c1.clear();
      c2.clear();
      c3.clear();
      c4.clear();
  }

  public String toString() {
    Object[] nested = {
      a1, a2, a3,
      b1, b2, b3,
      c1, c2, c3, c4
    };
    return Utils.toString(nested);
  }
}