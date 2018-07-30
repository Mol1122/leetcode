public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public List<Integer> previousPermuation(List<Integer> nums) {
        if (nums == null || nums.size() <= 1) {
            return nums;
        }
        int len = nums.size();
        int i = len - 1;
        while (i > 0 && nums.get(i) >= nums.get(i - 1)) {
            i--;
        }
        swapList(nums, i, len - 1);
        if (i != 0) {
            int j = i;
            while (nums.get(j) >= nums.get(i - 1)) {
                j++;
            }
            swapItem(nums, i - 1, j);
        }
        return nums;
    }
    
    private void swapList(List<Integer> nums, int start, int end) {
        while (start < end) {
            swapItem(nums, start, end);
            start++;
            end--;
        }
    }
    
    private void swapItem(List<Integer> nums, int start, int end) {
        int temp = nums.get(start);
        nums.set(start, nums.get(end));
        nums.set(end, temp);
    }
}

/* 算法：就是next permutation倒过来 */