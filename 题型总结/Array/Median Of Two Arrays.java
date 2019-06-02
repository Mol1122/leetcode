/* Given two arrays of integers, find the median value.

Assumptions

The two given array are not null and at least one of them is not empty
The two given array are not guaranteed to be sorted
Examples

A = {4, 1, 6}, B = {2, 3}, median is 3
A = {1, 4}, B = {3, 2}, median is 2.5 */

public class Solution {
  public double median(int[] a, int[] b) {
    if (a == null || b == null) {
        return 0.0;
    }
    Arrays.sort(a);
    Arrays.sort(b);
    int n = a.length + b.length;
    if (n % 2 == 0) {
        return (findKth(a, 0, b, 0, n / 2) + findKth(a, 0, b, 0, n / 2 + 1)) / 2.0;

    } else {
        return findKth(a, 0, b, 0, n / 2 + 1) / 1.0;
    }
  }

  private int findKth(int[] a, int a_start, int[] b, int b_start, int k) {
      if (a_start >= a.length) {
          return b[b_start + k - 1];
      }
      if (b_start == b.length) {
          return a[a_start + k - 1];
      }
      if (k == 1) {
          return Math.min(a[a_start], b[b_start]);
      }
      int a_key = a_start + k / 2 - 1 < a.length ? a[a_start + k / 2 - 1] : Integer.MAX_VALUE;
      int b_key = b_start + k / 2 - 1 < b.length ? b[b_start + k / 2 - 1] : Integer.MAX_VALUE;
      if (a_key < b_key) {
          return findKth(a, a_start + k / 2, b, b_start, k - k / 2);
      } else {
          return findKth(a, a_start, b, b_start + k / 2, k - k / 2);
      }
  }
}
//算法：binary search
//time: O(k) = O((n + m) / 2) = O(log(n + m)), space: O(log(n + m))