public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        //heap: add: O(logk) topK(klogk)
        // Queue<Integer> minheap = new PriorityQueue<>(k, new Comparator<Integer>() {
        //     public int compare(Integer a, Integer b) {
        //         return a - b;
        //     } 
        // });
        // for (int i = 0; i < nums.length; i++) {
        //     minheap.offer(nums[i]);
        //     if (minheap.size() > k) {
        //         minheap.poll();
        //     }
        // }
        // int[] results = new int[k];
        //  for (int i = 0; i < results.length; i++) {
        //      results[k - i - 1] = minheap.poll();
        //  }
        // return results;
         
        //quick sort: add:O(1), tepK(nlogn)
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        quickSort(nums, 0, nums.length - 1);
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            results[i] = nums[i];
        }
        return results;
    }
    
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}