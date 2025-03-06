public class Solution {
    /*
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0;
        Arrays.sort(nums);
        while (right < nums.length) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
            right++;
        }
        return left + 1;
    }
}

/* 算法：同向型双指针算法
** 难点：忘记sort,因为你依赖相邻的两个数是否相同，所以不可以不排序 
** 时间复杂度：O(nlogn)
** 空间复杂度；O(1) */