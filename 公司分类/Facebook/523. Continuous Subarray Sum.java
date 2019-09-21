/* Given a list of non-negative numbers and a target integer k, write a function to check if 
the array has a continuous subarray of size at least 2 that sums up to a multiple of k, 
that is, sums up to n*k where n is also an integer.

 

Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42. */

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> remainder2index = new HashMap<>();
        remainder2index.put(0, -1);
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (remainder2index.containsKey(sum)) {
                if (i - remainder2index.get(sum) > 1) {
                    return true;
                }
            } else { //必须要有这个else clause, 不然会错. 因为当有sum，但是长度小于2时会更新为更小的长度
                remainder2index.put(sum, i);
            }
        }
        return false;
    }
}

/* 算法：用map来保存余数对应的index在哪，如果已经出现过，并且长度大于2，那么肯定是true
** 难点：map里面存的不是sum，而是余数，因为只有余数能够判断是否为k的倍数 
** 时间复杂度：O(n) 
** 空间复杂度：O(n) */