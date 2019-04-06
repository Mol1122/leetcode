/* Given an unsorted integer array, each element is at most k step from its position after the array is sorted.

Can you sort this array with time complexity better than O(nlogn)? */
public class Solution {
  public int[] ksort(int[] nums, int k) {
      if (nums == null || nums.length == 0 || k == 0) {
          return nums;
      }
      int[] results = new int[nums.length];
      Queue<Integer> minheap = new PriorityQueue<>(k);
      
      for (int i = 0; i < k + 1; i++) {
          minheap.offer(nums[i]);
      }
      int index = 0;
      for (int i = k + 1; i < nums.length; i++) {
          results[index++] = minheap.poll();
          minheap.offer(nums[i]);
      }
      while (!minheap.isEmpty()) {
          results[index++] = minheap.poll();
      }
      return results;
  }
}

/* 时间复杂度：O(nlogk)
** 空间复杂度：O(k) */
