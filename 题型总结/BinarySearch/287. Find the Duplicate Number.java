/* Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once. */

class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //二分答案, time: O(logn * n), space: O(1)
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