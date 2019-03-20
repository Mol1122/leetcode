class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0, j = 0, k = nums.length - 1;
        
        while (j <= k) {
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {
                swap(nums, j, k);
                k--;
            }
        }
    }
    
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

/* 算法：三个挡板，四个区域
[0, i) -> 0, 
[i, j) -> 1,
[j, k] -> unexplored area
(k, nums.length - 1] -> 2

时间复杂度：O(n)
空间复杂度：O(1)
*/