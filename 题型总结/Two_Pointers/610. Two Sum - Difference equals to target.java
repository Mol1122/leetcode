/* Given an sorted array of integers, find two numbers that their difference equals to a target value.
Return a list with two number like [num1, num2] that the difference of num1 and num2 equals to target value, and num1 is less than num2.

It's guaranteed there is only one available solution.
Note: Requires O(1) space complexity to comple

Example
Example 1:

Input: nums = [2, 7, 15, 24], target = 5 
Output: [2, 7] 
Explanation:
(7 - 2 = 5)
Example 2:

Input: nums = [1, 1], target = 0
Output: [1, 1] 
Explanation:
(1 - 1 = 0) */

public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i] + target; //被减数
            if (map.containsKey(sum)) {
                int[] result = new int[2];
                result[0] = map.get(sum) + 1;
                result[1] = i + 1;
                return result;
            }
            int diff = nums[i] - target; //减数
            if (map.containsKey(diff)) {
                int[] result = new int[2];
                result[0] = map.get(diff) + 1;
                result[1] = i + 1;
                return result;
            }
            map.put(nums[i], i);
        }
        return null;
    }
}

/* 算法：利用HashMap,分情况被减数和减数去判断
** 时间复杂度：O(n)
** 空间复杂度：O(n) */

public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [num1, num2] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i] + target; //被减数
            if (set.contains(sum)) {
                return new int[]{sum, nums[i]};
            }
            int num = nums[i] - target; //减数
            if (set.contains(num)) {
                return new int[]{num, nums[i]};
            }
            set.add(nums[i]);
        }
        return new int[0];
    }
}