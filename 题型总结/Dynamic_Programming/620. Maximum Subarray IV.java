/* Given an integer arrays, find a contiguous subarray which has the largest sum and length should be greater or equal to given length k.
Return the largest sum, return 0 if there are fewer than k elements in the array.

Example
Example 1:

Input:
[-2,2,-3,4,-1,2,1,-5,3]
5
Output:
5
Explanation:
[2,-3,4,-1,2,1]
sum=5
Example 2:

Input:
[5,-10,4]
2
Output:
-1
Notice
Ensure that the result is an integer type.
k > 0 */

public class Solution {
    /**
     * @param nums: an array of integer
     * @param k: an integer
     * @return: the largest sum
     */
    public int maxSubarray4(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0;
        }
        int[] sums = new int[nums.length + 1];
        int min = 0, max = 0;
        for (int i = 0; i < k; i++) {
            max += nums[i];
        }
        
        for (int i = 1; i <= nums.length; i++) {
            sums[i] += sums[i - 1] + nums[i - 1];
            if (i >= k && sums[i] - min > max) {
                max = sums[i] - min;
            }
            if (i >= k) {
                min = Math.min(min, sums[i - k + 1]);
            }
        }
        return max;
    }
}

/* 算法：前缀和数组
** 难点：26行 sum[i - k + 1], 因为i+1和i-k+1隔了k个数. 面试的时候可以带特殊值进去算 */