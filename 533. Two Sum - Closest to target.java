public class Solution {
    /**
     * @param nums: an integer array
     * @param target: An integer
     * @return: the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int diff = Integer.MAX_VALUE;
        int left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        
        while (left < right) {
            int temp = Math.abs(target - (nums[left] + nums[right]));
            if (nums[left] + nums[right] < target) {
                diff = Math.min(diff, temp);
                left++;
            } else {
                diff = Math.min(diff, temp);
                right--;
            }
        }
        
        return diff;
    }
}