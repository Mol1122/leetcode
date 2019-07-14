/* Given an unsorted array of positive integers. Find the number of triangles that can be formed with three 
different array elements as three sides of triangles.

Assumptions:

The given array is not null and has length of at least 3.
Exmaples:

array = {4, 6, 3, 7}, the output should be 3. There are three triangles possible {3, 4, 6}, {4, 6, 7} 
and {3, 6, 7}. Note that {3, 4, 7} is impossible.
Preferred time complexity O(n ^ 2). */

public class Solution {
  public int numOfTriangles(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    Arrays.sort(nums);
    int count = 0;
    for (int i = nums.length - 1; i >= 2; i--) {
      int left = 0, right = i - 1;
      while (left < right) {
        if (nums[left] + nums[right] > nums[i]) {
          count += right - left;
          right--;
        } else {
          left++;
        }
      }
    }
    return count;
  }
}
//time: O(n^2), space: O(1)