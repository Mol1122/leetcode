public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int result = 0;
        int left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                result++;
                left++; //难点
                right--;
                while (left < nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (right >= 0 && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}

/* 算法：标准的相向型双指针 
** 难点：中间的while循环一定是要在判断玩target之后，否则会miss答案 
** 时间复杂度: O(n) */