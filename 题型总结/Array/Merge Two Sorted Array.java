/* Merge given amount of numbers from two sorted arrays.

Note that given amount of numbers are not larger than the length of the respective arrays.

Input: [1, 2, 3], 3, [2, 4, 6], 1

Output: [1,2, 2, 3] */

public class Solution {
  public int[] merge(int[] A, int m, int[] B, int n) {
    if (A == null || B == null) {
      return new int[0];
    }
    int[] results = new int[m + n];
    int i = 0, j = 0, index = 0;

    while (i < m && j < n) {
      if (A[i] < B[j]) {
        results[index++] = A[i++];
      } else {
        results[index++] = B[j++];
      }
    }
    while (i < m) {
      results[index++] = A[i++];
    }
    while (j < n) {
      results[index++] = B[j++];
    }
    return results;
  }
}
//time: O(n), space: O(1)