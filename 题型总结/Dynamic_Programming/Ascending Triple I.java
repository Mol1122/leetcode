public class Solution {
  public boolean existIJK(int[] nums) {
    if (nums == null || nums.length == 0) {
        return false;
    }
    int n = nums.length;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] >= nums[i]) {
                continue;
            }
            for (int k = 0; k < j; k++) {
                if (nums[k] >= nums[j]) {
                    continue;
                }
                return true;
            }
        }
    }
    return false;
  }
}
//time: O(n^3), space: O(1)
