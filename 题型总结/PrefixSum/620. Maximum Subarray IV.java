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

//Method 1
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
        
        
        // if (nums == null || nums.length < k) {
        //     return 0;
        // }
        // int n = nums.length;
        // int min = 0;
        // int max = 0;
        // for (int i = 0; i < k; i++) {
        //     max += nums[i];
        // }
        // int[] sums = new int[n + 1];
        
        // for (int i = 1; i <= n; i++) {
        //     sums[i] = sums[i - 1] + nums[i - 1];
        //     if (i >= k && sums[i] - min > max) {
        //         max = sums[i] - min;
        //     }
        //     if (i >= k) {
        //         min = Math.min(min, sums[i - k + 1]);
        //     }
        // }
        // return max;
        
        
        // if (nums == null || nums.length < k) {
        //     return 0;
        // }
        // int result = 0;
        // for (int i = 0; i < k; i++) {
        //     result += nums[i];
        // }
        // int min_prefix = 0;
        // int n = nums.length;
        // int[] sum = new int[n + 1];
        
        // sum[0] = 0;
        // for (int i = 1; i <= n; i++) {
        //     sum[i] = sum[i - 1] + nums[i - 1];
        //     if (i >= k && sum[i] - min_prefix > result) {
        //         result = Math.max(result, sum[i] - min_prefix);
        //     }
        //     if (i >= k) {
        //         min_prefix = Math.min(min_prefix, sum[i - k + 1]);
        //     }
        // }
        // return result;
    }
}

/* 算法：前缀和数组
** 难点：26行 sum[i - k + 1], 因为i+1和i-k+1隔了k个数. 面试的时候可以带特殊值进去算 */

//Method 2
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
        int min = 0, max = Integer.MIN_VALUE;

        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i -1] + nums[i - 1];

            if (i >= k) {
                max = Math.max(max, sums[i] - min);
                min = Math.min(min, sums[i - k + 1]);
            }
        }
        return max;
    }
}