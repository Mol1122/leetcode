/* Partition an unsorted integer array into three parts:

The front part < low
The middle part >= low & <= high
The tail part > high
Return any of the possible solutions.      

Example 1:

Input:
[4,3,4,1,2,3,1,2]
2
3
Output:
[1,1,2,3,2,3,4,4]
Explanation:
[1,1,2,2,3,3,4,4] is also a correct answer, but [1,2,1,2,3,3,4,4] is not
Example 2:

Input:
[3,2,1]
2
3
Output:
[1,2,3]*/

//Method 1
public class Solution {
    /*
     * @param nums: an integer array
     * @param low: An integer
     * @param high: An integer
     * @return: 
     */
    public void partition2(int[] nums, int low, int high) {
        if (nums == null || nums.length < 1) {
            return;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] < low) {
                left++;
            }
            while (left <= right && nums[right] >= low) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        
        right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] < high) {
                left++;
            }
            while (left <= right && nums[left] >= high) {
                right--;
            } 
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
    }
}

/* 算法：按照low, 和highpartition最容易的方法就是partition两次，这样达到了降维的目的
** 时间复杂度：O(n)
** 空间复杂度：O(1) */

//Method 2
public class Solution {
    /**
     * @param nums: an integer array
     * @param low: An integer
     * @param high: An integer
     * @return: nothing
     */
    public void partition2(int[] nums, int low, int high) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0, j = 0, k = nums.length - 1;

        while (j <= k) {
            if (nums[j] < low) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] >= low && nums[j] <= high) {
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