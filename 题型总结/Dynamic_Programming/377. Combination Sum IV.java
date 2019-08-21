/* Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers? */

class Solution {
    public int combinationSum4(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] f = new int[m + 1];
        f[0] = 1;
        
        for (int j = 1; j <= m; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    f[j] += f[j - nums[i]];
                }
            }
        }
        return f[m];
    }
}
//算法：背包问题
//time: O(n), space: O(n)