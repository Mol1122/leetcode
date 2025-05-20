/* Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is 
as minimum as possible.

Return the minimum difference(absolute value).

Assumptions:

The given integer array is not null and it has length of >= 2.
Examples:

{1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0 */

//Method 1
public class Solution {
  public int minDifference(int[] nums) {
    if (nums == null || nums.length == 0) {
        return Integer.MAX_VALUE;
    }
    int sum = 0;
    for (int num : nums) {
        sum += num;
    }
    int[] diff = {Integer.MAX_VALUE};
    List<Integer> prefix = new ArrayList<>();
    dfs(nums, 0, diff, 0, sum, prefix);
    return diff[0];
  }

  private void dfs(int[] nums, int index, int[] diff, int currSum, int sum, List<Integer> prefix) {
      if (prefix.size() == nums.length / 2) {
          if (Math.abs((sum - currSum) - currSum) < diff[0]) {
              diff[0] = Math.abs((sum - currSum) - currSum);
          }
          return;
      }
      if (index == nums.length) {
          return;
      }
      prefix.add(nums[index]);
      dfs(nums, index + 1, diff, currSum + nums[index], sum, prefix);
      prefix.remove(prefix.size() - 1);
      
      dfs(nums, index + 1, diff, currSum, sum, prefix);
  }
}
//time: O(2^n), space: O(n)
//难点：两个subset要是一样的size

//Method 2
public class Solution {
  public int minDifference(int[] nums) {
    if (nums == null || nums.length == 0) {
      return Integer.MAX_VALUE;
    }
    int[] min = {Integer.MAX_VALUE};
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    dfs(nums, 0, 0, sum, new ArrayList<>(), min);
    return min[0];
  }

  private void dfs(int[] nums, int index, int currSum, int sum, List<Integer> list, int[] min) {
    if (index == nums.length) {
      if (list.size() == nums.length / 2) {
        min[0] = Math.min(min[0], Math.abs(currSum - (sum - currSum)));
      }
      return;
    }
    list.add(nums[index]);
    dfs(nums, index + 1, currSum + nums[index], sum, list, min);
    list.remove(list.size() - 1);

    dfs(nums, index + 1, currSum, sum, list, min);
  }
}
