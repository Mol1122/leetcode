/* You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where 
nums[i - 1] > nums[i] for all 0 < i < nums.length.

Return the number of steps performed until nums becomes a non-decreasing array.

 

Example 1:

Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
Output: 3
Explanation: The following are the steps performed:
- Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
- Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
- Step 3: [5,4,7,11,11] becomes [5,7,11,11]
[5,7,11,11] is a non-decreasing array. Therefore, we return 3.
Example 2:

Input: nums = [4,5,7,7,13]
Output: 0
Explanation: nums is already a non-decreasing array. Therefore, we return 0. */

class Solution {
    public int totalSteps(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Deque<Pair> stack = new ArrayDeque<>();
        int max = 0;
        
        for (int num : nums) {
            int step = 0;
            while (!stack.isEmpty() && stack.peekLast().num <= num) {
                step = Math.max(step, stack.pollLast().step);
            }
            stack.offerLast(new Pair(num, stack.isEmpty() ? 0 : step + 1));
            max = Math.max(max, stack.peekLast().step);
        }
        return max;
    }
}

class Pair {
    int num, step;
    
    public Pair(int num, int step) {
        this.num = num;
        this.step = step;
    }
}
// 算法： 单调栈， time: O(n)