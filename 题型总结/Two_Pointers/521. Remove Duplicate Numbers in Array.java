/* Given an array of integers nums, logically remove the duplicate elements and return the length of the removed array n such that the first n elements of the array nums contain all the elements of the original array nums after de-duplication by the de-duplication operation.

You should:

Do it in place in the array.
Put the element after removing the repetition at the beginning of the array.
Return the number of elements after removing duplicate elements. 

Example 1:

Input:
nums = [1,3,1,4,4,2]
Output:
[1,3,4,2,?,?]
4
Explanation:

Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
Return the number of unique integers in nums => 4.
Actually we don't care about what you place in ?, we only care about the part which has no duplicate integers.

Example 2:

Input:
nums = [1,2,3]
Output:
[1,2,3]
3                      */

//Method 1:
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


//Method 2:
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0;
        Set<Integer> set = new HashSet<>();

        while (right < nums.length) {
            if (!set.contains(nums[right])) {
                set.add(nums[right]);
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right++;
                left++;
            } else {
                right++;
            }
        }
        return left;
    }
}
//time: O(n), space: O(n)