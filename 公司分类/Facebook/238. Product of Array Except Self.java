/* Given an array nums of n integers where n > 1,  return an array output such that output[i] is 
equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space 
for the purpose of space complexity analysis.) */

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] results = new int[nums.length];
        int count = 0;
        
        for (int num : nums) {
            if (num == 0) {
                count++;
            }
        }
        if (count >= 2) {
            return results;
        }
        int product = 1;
        for (int num : nums) {
            if (num != 0) {
                product *= num;
            }
        }
        if (count == 1) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    results[i] = product;
                }
            }
        }
        if (count == 0) {
            for (int i = 0; i < nums.length; i++) {
                results[i] = product / nums[i];
            }
        }
        return results;
    }
}
//time: O(n), space: O(1)