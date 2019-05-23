public class Solution {
  public int smallerPairs(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    Arrays.sort(nums);
    int i = 0, j = nums.length - 1;
    int count = 0;
    while (i < j) {
        if (nums[i] + nums[j] < target) {
            count += j - i;
            i++;
        } else {
            j--;
        }
    }
    return count;
  }
}
//time: O(nlogn), space: O(1)
