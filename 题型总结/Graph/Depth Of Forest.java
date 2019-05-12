/* Given an integer array A representing a forest, such that, A[i] means the parent index of index i, if A[i] == -1, it means index i is a root.

Determine what is the depth of the forest, the depth of the forest is the maximum depth of the trees in the forest.

Examples:

A = {2, 2, -1, 5, 5, -1, 3}, represnts the forest:

  2

 / \

0   1

and

    5

   /  \

  3    4

 /

6

The depth of the forest is 3(the depth of the second tree).

Assumptions:

The given array is not null or empty, all the elements in the array are in the range of [-1, N - 1] where N is the length of the array.
Corner Cases:

You should be able to identify that there could be a cycle in the forest, what if that is the case? Return -1 */

public class Solution {
  int maxDepth = Integer.MIN_VALUE;
  int[] memo;
  boolean hasCircle = false;
  public int depth(int[] forest) {
      if (forest == null || forest.length == 0) {
          return 0;
      }
      memo = new int[forest.length];
      for (int i = 0; i < forest.length; i++) {
          int depth = getDepth(forest, i);
          maxDepth = Math.max(maxDepth, depth);
      }
    
      return hasCircle == true ? -1 : maxDepth;
  }
  
  private int getDepth(int[] forest, int index) {
      if (memo[index] == -1) {
          hasCircle = true;
          return -1;
      }  
      if (memo[index] != 0) {
          return memo[index];
      }
      if (forest[index] == -1) {
          return 1;
      }
      memo[index] = -1;
      int depth = getDepth(forest, forest[index]);
      if (depth == -1) {
          memo[index] = -1;
          return -1;
      }
      memo[index] = depth + 1;
    
      return depth + 1;
  }
}
//time: O(n*height), space: O(height)
