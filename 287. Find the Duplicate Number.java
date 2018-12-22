class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //二分答案
        int start = 1, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(nums, mid) <= mid) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(nums, start) <= start) {
            return end;
        }
        return start;
  
        // if (nums == null || nums.length <= 1) {
        //     return -1;
        // }
        // int fast = nums[nums[0]];
        // int slow = nums[0];
        // while (slow != fast) {
        //     slow = nums[slow];
        //     fast = nums[nums[fast]];
        // }
        // fast = 0;
        // while (slow != fast) { //难点，跟LinkedList Cycle II不一样
        //     fast = nums[fast];
        //     slow = nums[slow];
        // }
        // return fast;
    }
    
    private int count(int[] nums, int mid) {
        int count = 0;
        for (int num : nums) {
            if (num <= mid) {
                count++;
            }
        }
        return count;
    }
}
/* 算法：跟LinkedList Cycle II一样，找是否有重复。如果有，那么一个从起始点开始，一个从first meet点开始，初次相遇的时候就是重复的值 */