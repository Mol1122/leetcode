public class Solution {
  public List<Integer> closest(int[] nums, int target) {
    List<Integer> results = new ArrayList<>();
    if (nums == null || nums.length == 0) {
        return results;
    }
    Arrays.sort(nums);
    int diff = Integer.MAX_VALUE;
    int i = 0, j = nums.length - 1;

    while (i < j) {
        if (nums[i] + nums[j] < target) {
            if (Math.abs(target - nums[i] - nums[j]) < diff) {
                results.clear();
                results.addAll(Arrays.asList(nums[i], nums[j]));
                diff = Math.abs(target - nums[i] - nums[j]);
            }
            i++;
        } else {
            if (Math.abs(target - nums[i] - nums[j]) < diff) {
                results.clear();
                results.addAll(Arrays.asList(nums[i], nums[j]));
                diff = Math.abs(target - nums[i] - nums[j]);
            }
            j--;
        }
    }
    return results;
  }
}
//time: O(nlogn), space: O(1)
