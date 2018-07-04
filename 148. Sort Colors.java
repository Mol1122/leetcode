public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int pl = 0, pr = nums.length - 1;
        int i = 0;
        
        while (i <= pr) {
            if (nums[i] == 0) {
                swap(nums, pl, i);
                i++;
                pl++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, pr, i);
                pr--; //难点：这个地方i不应该++，因为你并不清楚换过来的是0还是1，而且相邻的话会造成越界
            }
        }
    }
    
    private void swap(int[] nums, int p, int i) {
        int temp = nums[p];
        nums[p] = nums[i];
        nums[i] = temp;
    }
}

/* 算法：三分法，一个指针指向0的最后一个数，另一个指针指向2的第一个数，i用来遍历所有的数
** 难点: i 是在 <= pr的时候停下，因为pr实际上指向的是2的第一个数，但是--还要再往前一个数 
** 时间复杂度：O(n) */