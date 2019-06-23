/* Given a sorted array of positive numbers in ascending order, find the smallest positive integer value that cannot be represented as sum of elements of any sub-sequence of the input array.

Assumptions:

The given array is not null.
Examples:

array = {1, 3, 6, 10, 11, 15}, result is 2
array = {1, 1, 1, 1}, result is 5
  */

public class Solution {
  public int firstMissing(int[] nums) {
    int n = nums.length;
    int res = 1;
    for (int i = 0; i < n && nums[i] <= res; i++) {
      res = res + nums[i];
    }
    return res;
  }
}
//time: O(n), space: O(1)
/* case1. nums[i] > res: 说明res就是目前最小的
   case2. nums[i] <= res: 说明 elements from 0...i-1 can form res - 1
                               elements from 0...i can form res - 1 + nums[i]
                  
 */