/* Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

 

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
 */

 class Solution {
    public int[] dailyTemperatures(int[] temp) {
        int[] results = new int[temp.length];
        if (temp == null || temp.length == 0) {
            return results;
        }
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temp.length; i++) {
            while (!stack.isEmpty() && temp[i] > temp[stack.peekLast()]) {
                int index = stack.pollLast();
                results[index] = i - index;
            }
            stack.offerLast(i);
        }
        return results;
    }
}
//time: O(n), space: O(n)

//. 0  1. 2. 3. 4. 5. 6.  7
// [73,74,75,71,69,72,76,73]
//.                   i
//. 1.  1 4  2   1. 1.  0. 0
// stack: 6, 7