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
            while (!stack.isEmpty() && stack.peekLast().num <= num) { //<=是因为相同的话要把它从stack拿出。 栈里只存单调递减的数
                step = Math.max(step, stack.pollLast().step); //直到stack里的上一个数，有多少个数是decreasing
            }
            stack.offerLast(new Pair(num, stack.isEmpty() ? 0 : step + 1)); //从上一个非递减数开始算，到当前这个数，第几个decreasing number  
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
/*
假设当前数nums[i] < nums[i - 1]. 那么就让i往后走，看后面有多少个数比nums[i - 1]小。需要往下走的同时回头看，因此想到单调栈
*/

// [5,3,4,4,7,3,6,11,8,5,11]
//                     i
//. 0 1 2 3 0 1 2  0 1 1 2 
// 算法： 单调栈， time: O(n)