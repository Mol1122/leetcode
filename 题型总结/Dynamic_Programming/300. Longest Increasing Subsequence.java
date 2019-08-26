/* Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

Assumptions

A is not null
Examples
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: 4
Because [2, 3, 4, 5] is the longest ascending subsequence. */

public class Solution {
  public int longest(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int[] f = new int[nums.length];
      int max = Integer.MIN_VALUE;
    
      for (int i = 0; i < nums.length; i++) {
          f[i] = 1;
          for (int j = 0; j < i; j++) {
              if (nums[j] < nums[i]) {
                  f[i] = Math.max(f[i], f[j] + 1);
              }
          }
          max = Math.max(max, f[i]);
      }
      return max;
  }
}
//f[i] = max length of increasing subsequence from index 0 to index i, in
//not necessarily include index i
//time: O(n^2), space: O(n)

public class Solution {
  public int longest(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int[] minLast = new int[nums.length + 1];
    minLast[0] = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length + 1; i++) {
        minLast[i] = Integer.MAX_VALUE;
    }
    //find the first minLast >= num[i]
    for (int i = 0; i < nums.length; i++) {
        int index = binarySearch(minLast, nums[i]);
        minLast[index] = nums[i];
    }

    for (int i = minLast.length - 1; i >= 0; i--) {
        if (minLast[i] != Integer.MAX_VALUE) {
            return i;
        }
    }
    return 0;
  }

  private int binarySearch(int[] minLast, int num) {
    int start = 0, end = minLast.length - 1;
    while (start + 1 < end) {
        int mid = start + (end - start) / 2;
        if (minLast[mid] <= num) {
            start = mid;
        } else {
            end = mid;
        }
    }
    if (minLast[start] >= num) {
        return start;
    }
    return end;
  }
}
// minLast[i]: i is the max length of the longest ascending sequence ending at nums[i], 
// minLast[i] is the last ending number, 之前遍历过的array, 长度为i时的结尾是什么
//time: O(nlogn), space: O(n)