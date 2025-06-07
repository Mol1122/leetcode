/* Description
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


The subarray should contain at least one number

Example
Example 1:

Input:

nums = [1,2,3]
k = 1
Output:

6
Explanation:

1 + 2 + 3 = 6
Example 2:

Input:

nums = [-1,4,-2,3,-2,3]
k = 2
Output:

8
Explanation:

4 + (3 + -2 + 3) = 8

 */

public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0;
        }
        int n = nums.length;
        int[][] global = new int[k + 1][n + 1];
        int[][] local = new int[k + 1][n + 1];
        
        for (int i = 1; i <= k; i++) {
            local[i][i - 1] = Integer.MIN_VALUE;
            for (int j = i; j <= n; j++) {
                local[i][j] = Math.max(local[i][j - 1], global[i - 1][j - 1]) + nums[j - 1]; //如果加最后一个数，分组够k, 不够k
                if (j == i) { //难点
                    global[i][j] = local[i][j];
                } else {
                    global[i][j] = Math.max(global[i][j - 1], local[i][j]); //要不要加最后一个数
                }
            }
        }
        return global[k][n];
    }
}

/* 算法：划分类DP题，凡是需要k个组的时候都是用global, local array */