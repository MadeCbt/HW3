/*
 * *** YOUR NAME GOES HERE / YOUR SECTION NUMBER ***
 *
 * This java file contains several simple tree problems that need to be
 * implemented using the Java Collections Framework.
 *
 */

 import java.util.*;

 public class TreeProblems {
 
   /**
    * Method different()
    *
    * Given two TreeSets of integers, return a TreeSet containing all elements 
    * that are NOT in both sets.
    */
   public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {
     // YOUR CODE GOES HERE
     // -----------------------------------------------------
     // 1. Create temporary TreeSet copies of setA and setB.
     // 2. Remove common elements from each temporary set.
     // 3. Create a new TreeSet and add the remaining (unique) elements.
     // 4. Return the resulting set.
     // -----------------------------------------------------
     TreeSet<Integer> tempA = new TreeSet<>(setA);
     TreeSet<Integer> tempB = new TreeSet<>(setB);
     tempA.removeAll(setB);
     tempB.removeAll(setA);
     TreeSet<Integer> result = new TreeSet<>();
     result.addAll(tempA);
     result.addAll(tempB);
     return result;
   }
 
   /**
    * Method removeEven()
    *
    * Given a TreeMap with integer keys and String values,
    * remove all key-value pairs where the key is even.
    */
   public static void removeEven(Map<Integer, String> treeMap) {
     // YOUR CODE GOES HERE
     // -----------------------------------------------------
     // 1. Create an iterator for the key set of the map.
     // 2. Iterate through the keys.
     // 3. If a key is even, remove it using the iterator's remove method.
     // -----------------------------------------------------
     Iterator<Integer> it = treeMap.keySet().iterator();
     while (it.hasNext()) {
       int key = it.next();
       if (key % 2 == 0) {
         it.remove();
       }
     }
   }
 
   /**
    * Method treesEqual()
    *
    * Given two TreeMaps (with integer keys and String values),
    * return true if they are equal, otherwise false.
    */
   public boolean treesEqual(Map<Integer, String> tree1, Map<Integer, String> tree2) {
     // YOUR CODE GOES HERE
     // -----------------------------------------------------
     // 1. Use the Map's built-in equals() method to compare the two maps.
     // 2. Return the result of the comparison.
     // -----------------------------------------------------
     return tree1.equals(tree2);
   }
 
 } // end TreeProblems class
 