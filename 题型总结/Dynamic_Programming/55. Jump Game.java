/* Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). 
Determine if you are able to reach the last index.

Assumptions

The given array is not null and has length of at least 1.
Examples

{1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)

{2, 1, 1, 0, 2}, we are not able to reach the end of array */

public class Solution {
  public boolean canJump(int[] nums) {
      if (nums == null || nums.length == 0) {
          return true;
      }
      boolean[] f = new boolean[nums.length];
      f[0] = true;
    
      for (int i = 1; i < nums.length; i++) {
          for (int j = 0; j < i; j++) {
              if (f[j] && j + nums[j] >= i) {
                  f[i] = true;
                  break;
              }
          }
      }
      return f[nums.length - 1];
  }
}
//canJump[i] = 是否能从index 0跳到index i
//time: O(n^2), space: O(n)
