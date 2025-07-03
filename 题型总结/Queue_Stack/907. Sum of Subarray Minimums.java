/* Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444
  */

class Solution {
    public int sumSubarrayMins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Deque<int[]> stack = new ArrayDeque<>(); //[num, index]
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] <= stack.peekLast()[0]) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                res[i] = nums[i] * (i + 1);
            } else {
                int prevIndex = stack.peekLast()[1];
                res[i] = res[prevIndex] + nums[i] * (i - prevIndex);
            }
            stack.offerLast(new int[]{nums[i], i});
        }
        long MOD = (long)Math.pow(10, 9) + 7;
        long sum = 0;
        for (int num : res) {
            sum = (sum + num) % MOD;
        }
        return (int)sum;
    }
}

/*
        0  1  2  3  4 
        3, 1, 2, 7, 4
        i
stack: 1, 2,
算以当前数为最小数存在在几个subarray里
0:    [3] -> 3 * 1   
1:    [3,1] [1] ->  1 * 2
2:    [3, 1, 2] [1, 2] [2]  -> res[1] + 2 * (2 - 1)
7:    [3, 1, 2, 7], [1, 2, 7], [2, 7], [7] -> res[2] + 7 * (3 - 2)
4:    [3, 1, 2, 7, 4], [1, 2, 7, 4], [2, 7, 4], [7, 4], [4]  -> res[2] + 4 * (4 - 2)

*/
//单调递增栈， time: O(n), space: o(n)