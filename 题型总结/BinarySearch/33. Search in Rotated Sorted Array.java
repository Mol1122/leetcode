/* Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.

For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.

Assumptions

There are no duplicate elements in the array.
Examples

A = {3, 4, 5, 1, 2}, T = 4, return 1
A = {1, 2, 3, 4, 5}, T = 4, return 3
A = {3, 5, 6, 1, 2}, T = 4, return -1
Corner Cases

What if A is null or A is of zero length? We should return -1 in this case. */

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return - 1;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[start] < nums[mid]) { //pivot on the right, and the first part is increasing. left is sorted
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else { //right is sorted
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}

/* 算法：二分。因为并不知道pivot在哪里，但是可以肯定的是，pivot肯定在mid的左边或者右边。这就意味着，mid的左边或者右边一定是升序的.
时间复杂度：O(logn), space: O(1)
*/

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int peakIndex = getPeak(nums);
        int findInLeft = findTarget(nums, 0, peakIndex, target);

        if (findInLeft != -1) {
            return findInLeft;
        }
        return findTarget(nums, peakIndex + 1, nums.length - 1, target);
    }

    private int findTarget(int[] nums, int start, int end, int target) {
        if (end < start || start < 0) {
            return -1;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    private int getPeak(int[] nums) {
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return nums[start] > nums[end] ? start : end;
    }
}
//time: O(logn), space: O(1)