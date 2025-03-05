/* Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number. 
Please return the number of pairs.

Example
Example 1:

Input: nums = [1,1,2,45,46,46], target = 47 
Output: 2
Explanation:

1 + 46 = 47
2 + 45 = 47
Example 2:

Input: nums = [1,1], target = 2 
Output: 1
Explanation:
1 + 1 = 2 */

public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int result = 0;
        int left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                result++;
                left++; //难点
                right--;
                while (left < nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (right >= 0 && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}

/* 算法：标准的相向型双指针 
** 难点：中间的while循环一定是要在判断玩target之后，否则会miss答案 
** 时间复杂度: O(nlogn) */

public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int start = 0, end = nums.length - 1;
        int count = 0;

        while (start < end) {
            if (nums[start] + nums[end] == target) {
                count++;
                int left = nums[start], right = nums[end];
                while (start < end && nums[start] == left) {
                    start++;
                }
                while (start < end && nums[end] == right) {
                    end--;
                }           
            } else if (nums[start] + nums[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        return count;
    }
}
//time: Onlogn, space: O(1)