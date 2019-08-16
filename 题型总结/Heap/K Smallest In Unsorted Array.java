/* Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.

Assumptions

A is not null
K is >= 0 and smaller than or equal to size of A
Return

an array with size K containing the K smallest numbers in ascending order
Examples

A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3} */

public class Solution {
  public int[] kSmallest(int[] nums, int k) {
      int[] results = new int[k];
      if (nums == null || nums.length == 0 || k > nums.length || k == 0) {
          return new int[0];
      }
      Queue<Integer> maxheap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
          public int compare(Integer a, Integer b) {
              return b - a;
          }
      });
      for (int i = 0; i < k; i++) {
          maxheap.offer(nums[i]);
      }
      for (int i = k; i < nums.length; i++) {
          if (nums[i] < maxheap.peek()) {
              maxheap.poll();
              maxheap.offer(nums[i]);
          }
      }
      int index = k - 1;
      while (!maxheap.isEmpty()) {
          results[index--] = maxheap.poll();
      }
      return results;
  }
}

/* 时间复杂度：O(k + (n - k)log(k))
** 空间复杂度：O(k) */