/* Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]. */

public class Solution {
  public String[] summaryRanges(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new String[0];
    }
    List<String> results = new ArrayList<>();
    int i = 0, j = 0;

    while (j < nums.length) {
      if (nums[i] == nums[j] || nums[j] == nums[j - 1] + 1) {
        j++;
      } else {
        if (j - i == 1) {
          results.add(nums[i] + "");
        } else {
          results.add(nums[i] + "->" + nums[j - 1]);
        }
        i = j;
      }
    }
    if (j - i == 1) {
      results.add(nums[i] + "");
    } else {
      results.add(nums[i] + "->" + nums[j - 1]);
    }

    String[] ans = new String[results.size()];
    for (i = 0; i < results.size(); i++) {
      ans[i] = results.get(i);
    }
    return ans;
  }
}
//time: O(n), space: O(n)