/* Given two sorted arrays of integers, find the Kth smallest number.

Assumptions

The two given arrays are not null and at least one of them is not empty

K >= 1, K <= total lengths of the two sorted arrays

Examples

A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.

A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2. */

public class Solution {
  public int kth(int[] A, int[] B, int k) {
    if (A == null || B == null || A.length + B.length < k) {
      return -1;
    }
    return findKth(A, 0, B, 0, k);
  }

  private int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
    if (A_start >= A.length) {
      return B[B_start + k - 1];
    }
    if (B_start >= B.length) {
      return A[A_start + k - 1];
    }
    if (k == 1) {
      return Math.min(A[A_start], B[B_start]);
    }
    int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
    int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;
    if (A_key < B_key) {
      return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
    } else {
      return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
    }
  }
}
//time: O(logn), space: O(logn)