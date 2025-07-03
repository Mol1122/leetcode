/* Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.

An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.

We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.

 

Example 1:

Input: nums = [3,5,2,6], k = 2
Output: [2,6]
Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
Example 2:

Input: nums = [2,4,3,3,5,4,9,6], k = 4
Output: [2,3,3,4]*/

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int[] result = new int[k];
        if (nums == null || nums.length < k) {
            return result;
        }
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < stack.peekLast() && nums.length - i + stack.size() > k) { //nums.length - i = 没有visit的数有多少个，nums.length - i + stack.size() = 剩下的数+已经在stack里的数
                stack.pollLast();
            }
            if (stack.size() < k) {
                stack.offerLast(nums[i]);
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = stack.pollLast();
        }
        return result;
    }
}
// [71,18,52,29,55,73,24,42,66,8,80,2] k = 3
// 8,80,2
//time: O(n), space: O(n)