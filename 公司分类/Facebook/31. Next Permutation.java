/* Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1 */

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        int i = n - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i > 0) {
            int j = n - 1;
            while (nums[j] <= nums[i - 1]) {
                j--;
            }
            swapItem(nums, j , i - 1);
        }
        swapList(nums, i, n - 1);
    }
    
    private void swapItem(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void swapList(int[] nums, int i, int j) {
        while (i < j) {
            swapItem(nums, i, j);
            i++;
            j--;
        }
    }
}

/* 
从末尾往左走，如果一直递增，例如...9,7,5，那么下一个排列一定会牵扯到左边更多的数，直到一个非递增数为止，例如...6,9,7,5。对于原数组的变化就只到6这里，和左侧其他数再无关系。6这个位置会变成6右侧所有数中比6大的最小的数，而6会进入最后3个数中，且后3个数必是升序数组。

所以算法步骤如下：

从右往左遍历数组nums，直到找到一个位置i，满足nums[i] > nums[i - 1]或者i为0。
i不为0时，用j再次从右到左遍历nums，寻找第一个nums[j] > nums[i - 1]。而后交换nums[j]和nums[i - 1]。注意，满足要求的j一定存在！且交换后nums[i]及右侧数组仍为降序数组。
将nums[i]及右侧的数组翻转，使其升序。
*/