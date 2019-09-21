/* Given an array of integers and an integer k, you need to find the total number of continuous 
subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2 */

class Solution {
    public int subarraySum(int[] nums, int k) {
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
            sum2count.putIfAbsent(sum, 0);
            sum2count.put(sum, sum2count.get(sum) + 1);
        }
        return count;
    }
}
//time: O(n), space: O(n)