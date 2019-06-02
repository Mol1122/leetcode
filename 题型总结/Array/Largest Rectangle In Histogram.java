/* Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. 
Find the largest rectangular area that can be formed in the histogram.

Assumptions

The given array is not null or empty
Examples

{ 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)

 */
 
public class Solution {
  public int largest(int[] heights) {
    if (heights == null || heights.length == 0) {
        return 0;
    }
    Deque<Integer> stack = new ArrayDeque<>();
    int max = 0;
    for (int i = 0; i <= heights.length; i++) {
        int height = i < heights.length ? heights[i] : -1;
        while (!stack.isEmpty() && height < heights[stack.peekLast()]) {
            int h = heights[stack.pollLast()];
            int w = stack.isEmpty() ? i : i - stack.peekLast() - 1;
            max = Math.max(max, h * w);
        }
        stack.offerLast(i);
    }
    return max;
  }
}
//time: O(n), space: O(n)