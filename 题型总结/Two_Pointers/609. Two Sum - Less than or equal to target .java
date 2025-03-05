/* Given an array of integers, find how many pairs in the array such that their sum is less than or equal to a specific target number. 
Please return the number of pairs.

Example
Example 1:

Input: nums = [2, 7, 11, 15], target = 24. 
Output: 5. 
Explanation:
2 + 7 < 24
2 + 11 < 24
2 + 15 < 24
7 + 11 < 24
7 + 15 < 24
Example 2:

Input: nums = [1], target = 1. 
Output: 0.  */

public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, cnt = 0;
        while (left < right) {
        	int val = nums[left] + nums[right];
        	if (val > target) {
        		right--;
        	} else {
        		cnt += right - left;
        		left++;
        	}
        }
        return cnt;
    }
}

//Method 2: 
public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int start = 0, end = nums.length - 1;
        int count = 0;

        while (start < end) {
            if (nums[start] + nums[end] <= target) {
                count += end - start;
                start++;
            } else {
                end--;
            }
        }
        return count;
    }
}
//time: O(nlogn), space: O(1)