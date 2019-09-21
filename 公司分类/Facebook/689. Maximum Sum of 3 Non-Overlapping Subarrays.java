/* In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). 
If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but a */

public class Solution {
  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    if (nums == null || nums.length < k) {
      return new int[0];
    }
    int n = nums.length, maxSum = 0;
    int[] sums = new int[n + 1], posLeft = new int[n], posRight = new int[n], ans = new int[3];

    for (int i = 0; i < n; i++) {
      sums[i + 1] = sums[i] + nums[i];
    }
    //遍历到index为i的数，左边最大的subarray的起始位置是多少
    for (int i = k, total = sums[k] - sums[0]; i < n; i++) {
      if (sums[i + 1] - sums[i + 1 - k] > total) {
        posLeft[i] = i + 1 - k;
        total = sums[i + 1] - sums[i + 1 - k];
      } else {
        posLeft[i] = posLeft[i - 1]; //找不到更大就跟之前一样
      }
    }
    //遍历到index为n-k的数，右边最大的subarray的起始位置是多少
    posRight[n - k] = n - k;
    for (int i = n - k - 1, total = sums[n] - sums[n - k]; i >= 0; i--) {
      if (sums[i + k] - sums[i] > total) {
        posRight[i] = i;
        total = sums[i + k] - sums[i];
      } else {
        posRight[i] = posRight[i + 1];
      }
    }
    for (int i = k; i <= n - 2 * k; i++) {
      int l = posLeft[i - 1], r = posRight[i + k];
      int sum = (sums[l + k] - sums[l]) + (sums[i + k] - sums[i]) + (sums[r + k] - sums[r]);
      if (sum > maxSum) {
        maxSum = sum;
        ans[0] = l;
        ans[1] = i;
        ans[2] = r;
      }
    }
    return ans;
  }
}
//time: O(n), space: O(n)