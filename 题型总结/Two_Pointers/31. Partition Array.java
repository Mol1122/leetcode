public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        return partition(nums, 0, nums.length - 1, k);
    }
    
    private int partition(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int i = start, j = end;
    
        while (i <= j) {
            while (i <= j && nums[i] < k) {
                i++;
            }
            while (i <= j && nums[j] >= k) {
                j--;
            }
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        return j + 1;
    }
}

/* 算法：就是quick sort里的partition
** 难点：return的是index,并且j指的是第一个>=k,但是因为while条件是i<=j, 所以返回的时候要加1 
** 时间复杂度：O(n) */