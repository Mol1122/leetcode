/* Given an array of non-negative integers, each of them representing the height of a board perpendicular to the horizontal line, the distance between any two adjacent boards is 1. Consider selecting two boards such that together with the horizontal line they form a container. Find the volume of the largest such container.

Assumptions

The given array is not null and has size of at least 2
Examples

{ 2, 1, 3, 1, 2, 1 }, the largest container is formed by the two boards of height 2, the volume of the container is 2 * 4 = 8. */

public class Solution {
  public int largest(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int left = 0, right = nums.length - 1;
    int max = 0;

    while (left < right) {
      int temp = (right - left) * Math.min(nums[left], nums[right]);
      max = Math.max(max, temp);
      if (nums[left] < nums[right]) {
        left++;
      } else {
        right--;
      }
    }
    return max;
  }
}
//time: O(n), space: O(1)