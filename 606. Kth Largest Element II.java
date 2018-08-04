public class Solution {
    /**
     * @param nums: an integer unsorted array
     * @param k: an integer from 1 to n
     * @return: the kth largest element
     */
    public int kthLargestElement2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(k);
        for (int i = 0; i < nums.length; i++) {
            minheap.offer(nums[i]);
            if (minheap.size() > k) {
                minheap.poll();
            }
        }
        return minheap.peek();
    }
}
//qick select, add (1), kth largest O(k) -> k大的时候好
//minheap: add O(logk), kth largest O(klogk) -> k小的时候好