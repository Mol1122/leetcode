/* Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example
Example1

Input: nums = [1,1,1] and k = 2
Output: 2
Explanation:
subarray [0,1] and [1,2]
Example2

Input: nums = [2,1,-1,1,2] and k = 3
Output: 4
Explanation:
subarray [0,1], [1,4], [0,3] and [3,4] */

public class Solution {
    /**
     * @param nums: a list of integer
     * @param k: an integer
     * @return: return an integer, denote the number of continuous subarrays whose sum equals to k
     */
    public int subarraySumEqualsK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> sum2count = new HashMap<>();
        sum2count.put(0, 1);
        int sum = 0, count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum2count.containsKey(sum - k)) {
                count += sum2count.get(sum - k);
            }
            sum2count.put(sum, sum2count.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
//time: O(n), space: O(n)