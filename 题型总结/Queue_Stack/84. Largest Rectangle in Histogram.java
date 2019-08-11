/* Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in the histogram.

Assumptions

The given array is not null or empty
Examples

{ 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4) */

public class Solution {
  public int largest(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    Deque<Integer> stack = new ArrayDeque<>();
    int max = 0;

    for (int i = 0; i <= heights.length; i++) {
      int curr = i == heights.length ? -1 : heights[i];
      while (!stack.isEmpty() && curr < heights[stack.peekLast()]) {
        int h = heights[stack.pollLast()];
        int w = stack.isEmpty() ? i : i - stack.peekLast() - 1;
        max = Math.max(max, h * w);
      }
      stack.offerLast(i);
    }
    return max;
  }
}
// 算法：单调栈。需要找到某个值最近的最大值或者最小值用单调栈。判断递增还是递减，就是看我暂时要保存什么值。
//如果需要保存比当前大的，直到找到第一个比当前小的就是递增
//time: O(n), space: O(n)