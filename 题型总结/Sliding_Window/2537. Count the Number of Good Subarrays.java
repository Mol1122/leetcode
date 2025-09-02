/* Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.
Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs. */

class Solution {
    public long countGood(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long ans = 0, pair = 0;
        int j = 0;
        Map<Integer, Integer> num2count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && pair < k) {
                pair += num2count.getOrDefault(nums[j], 0);
                num2count.put(nums[j], num2count.getOrDefault(nums[j], 0) + 1);
                j++;
            }
            if (pair >= k) {
                ans += nums.length - j + 1;
            }
            num2count.put(nums[i], num2count.get(nums[i]) - 1);
            pair -= num2count.get(nums[i]);
        }
        return ans;
    }
}
//time: O(n), space: O(n)