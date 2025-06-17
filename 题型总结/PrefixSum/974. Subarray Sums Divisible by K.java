/* Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

 

Example 1:

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
Example 2:

Input: nums = [5], k = 9
Output: 0 */

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0, count = 0;
        Map<Integer, Integer> remainder2count = new HashMap<>();
        remainder2count.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int remainder = sum % k;
            if (remainder < 0) { //in case remainder is negative
                remainder += k;
            }

            if (remainder2count.containsKey(remainder)) {
                count += remainder2count.get(remainder);     
            }
            remainder2count.putIfAbsent(remainder, 0);
            remainder2count.put(remainder, remainder2count.get(remainder) + 1);
        }
        return count;
    }
} 
//time: O(n), space: O(k)

//----------
//          ******
//----------------curr sum