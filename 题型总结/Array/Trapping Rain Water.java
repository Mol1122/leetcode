/* Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6. */

public class Solution {
  public int trapWater(int[] heights) {
    if (heights == null || heights.length < 2) {
      return 0;
    }
    int leftHeight = heights[0], rightHeight = heights[heights.length - 1];
    int left = 0, right = heights.length - 1;
    int sum = 0;

    while (left < right) {
      if (leftHeight < rightHeight) {
        left++;
        if (leftHeight > heights[left]) {
          sum += leftHeight - heights[left];
        } else {
          leftHeight = heights[left];
        }
      } else {
        right--;
        if (rightHeight > heights[right]) {
          sum += rightHeight - heights[right];
        } else {
          rightHeight = heights[right];
        }
      }
    }
    return sum;
  }
}
//time: O(n), space: O(1)
