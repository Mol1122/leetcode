public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, cnt = 0;
        while (left < right) {
        	int val = nums[left] + nums[right];
        	if (val > target) {
        		right--;
        	} else {
        		cnt += right - left;
        		left++;
        	}
        }
        return cnt;
    }
}