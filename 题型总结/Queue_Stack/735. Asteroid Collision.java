/* We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 */

 class Solution {
    public int[] asteroidCollision(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Deque<Integer> stack = new ArrayDeque<>(); //stores each number
        Deque<Integer> temp = new ArrayDeque<>();

        for (int num : nums) {
            if (stack.isEmpty()) {
                stack.offerLast(num);
                continue;
            }
            if (num > 0 || num < 0 && stack.peekLast() < 0) {
                stack.offerLast(num);
                continue;
            }
            while (!stack.isEmpty() && stack.peekLast() > 0 && num < 0) {
                int last = stack.pollLast();
                int absLast = Math.abs(last), absCurr = Math.abs(num);
                if (absLast > absCurr) {
                    stack.offerLast(last);
                    break;
                } else if (absLast < absCurr) {
                    if (stack.isEmpty() || stack.peekLast() < 0) {
                        stack.offerLast(num);
                        break;
                    } 
                } else {
                    break;
                }
            }
        }

        while (!stack.isEmpty()) {
            temp.offerLast(stack.pollLast());
        }
        int[] results = new int[temp.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = temp.pollLast();
        }
        return results;
    }
}
//time: O(n), space: O(n)