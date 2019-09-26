/* Given a circular array (the next element of the last element is the first element 
of the array), print the Next Greater Number for every element. The Next Greater 
Number of a number x is the first greater number to its traversing-order next in 
the array, which means you could search circularly to find its next greater number. 
If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2. */

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int n = nums.length;
        int[] results = new int[n];
        Arrays.fill(results, -1);
        Deque<Integer> stack = new ArrayDeque<>(); //store index
        
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peekLast()] < num) {
                results[stack.pollLast()] = num;
            }
            if (i < n) { //注意
                stack.offerLast(i);
            }
        }
        return results;
    }
}
//time: O(n), space: o(n)