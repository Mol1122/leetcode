/* Given an array of integers, find two non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

Example
Example 1:

Input:
[1, 3, -1, 2, -1, 2]
Output:
7
Explanation:
the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2].
Example 2:

Input:
[5,4]
Output:
9
Explanation:
the two subarrays are [5] and [4].
Challenge
Can you do it in time complexity O(n) ?

Notice
The subarray should contain at least one number */

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        // if (nums == null || nums.size() == 0) {
        //     return 0;
        // }
        // int n = nums.size();
        // int[] left = new int[n];
        // int[] right = new int[n];
        
        // int max = Integer.MIN_VALUE, sum = 0, min = 0;
        // for (int i = 0; i < n; i++) {
        //     sum += nums.get(i);
        //     max = Math.max(max, sum - min);
        //     min = Math.min(min, sum);
        //     left[i] = max;
        // }
        // max = Integer.MIN_VALUE;
        // sum = 0;
        // min = 0;
        // for (int i = n - 1; i >= 0; i--) {
        //     sum += nums.get(i);
        //     max = Math.max(max, sum - min);
        //     min = Math.min(min, sum);
        //     right[i] = max;
        // }
        // max = Integer.MIN_VALUE;
        // for (int i = 0; i < n - 1; i++) {
        //     max = Math.max(max, left[i] + right[i + 1]);
        // }
        // return max;
        
        
        /////////////////////////
        int min = 0;
        int max = Integer.MIN_VALUE;
        int[] left = new int[nums.size()]; //max
        int sum = 0;
        
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            left[i] = max;
        }
        
        min = 0;
        int[] right = new int[nums.size()]; //max
        sum = 0;
        max = Integer.MIN_VALUE;
        
        for (int i = nums.size() - 1; i >= 0; i--) {
            sum += nums.get(i);
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            right[i] = max;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (left[i] + right[i + 1] > ans) {
                ans = left[i] + right[i + 1];
            }
        }
        return ans;
    }
}