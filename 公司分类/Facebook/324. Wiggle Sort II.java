/* Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2]. */

class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        Arrays.sort(nums);
        int mid = 0;
        if (nums.length % 2 == 0) {
            mid = nums.length / 2;
        } else {
            mid = nums.length / 2 + 1;
        }
        int[] ans = new int[nums.length];
        int left = mid - 1, right = nums.length - 1;
        int index = 0;
        
        while (left >= 0 && right >= mid) {
            if (index % 2 == 0) {
                ans[index++] = nums[left--];
            } else {
                ans[index++] = nums[right--];
            }
        }
        if (left >= 0) {
            ans[index++] = nums[left--];
        }
        if (right >= mid) {
            ans[index++] = nums[right--];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }
}
//time: O(nlogn), space: O(n)