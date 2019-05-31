/* Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).

Assumptions

In each of the two sorted arrays, there could be duplicate numbers.
Both two arrays are not null.
Examples

A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2] */
public class Solution {
  public List<Integer> common(List<Integer> A, List<Integer> B) {
      //time: O(n + m), space: O(1)
      List<Integer> results = new ArrayList<>();
      if (A == null || B == null) {
          return results;
      }
      int i = 0, j = 0;
      while (i < A.size() && j < B.size()) {
          if (A.get(i) == B.get(j)) {
              results.add(A.get(i));
              i++;
              j++;
          } else if (A.get(i) < B.get(j)) {
              i++;
          } else {
              j++;
          }
      }
      return results;  
  }
}
