/* ou are given an array of integers nums and an integer k.

Return the maximum sum of a subarray of nums, such that the size of the subarray is divisible by k.

 

Example 1:

Input: nums = [1,2], k = 1

Output: 3

Explanation:

The subarray [1, 2] with sum 3 has length equal to 2 which is divisible by 1.

Example 2:

Input: nums = [-1,-2,-3,-4,-5], k = 4

Output: -10

Explanation:

The maximum sum subarray is [-1, -2, -3, -4] which has length equal to 4 which is divisible by 4.

Example 3:

Input: nums = [-5,1,2,-3,4], k = 2

Output: 4

Explanation:

The maximum sum subarray is [1, 2, -3, 4] which has length equal to 4 which is divisible by 2.

 */

 class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long sum = 0, max = Long.MIN_VALUE;
        Map<Integer, Long> remainder2sum = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int remainder = i % k;
            if (remainder2sum.containsKey(remainder)) {
                max = Math.max(max, sum - remainder2sum.get(remainder));
            }
            if ((i + 1) % k == 0) {
                max = Math.max(max, sum);
            }

            remainder2sum.putIfAbsent(remainder, Long.MAX_VALUE);
            remainder2sum.put(remainder, Math.min(remainder2sum.get(remainder), sum));
        }
        return max;
    }
}
//time: O(n), space: O(k)
//        i % k = 2
//--------min
//        ****
//------------curr sum
//            j % k = 2