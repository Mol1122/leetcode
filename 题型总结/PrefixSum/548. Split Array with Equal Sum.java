/* Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5. 
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1 */

class Solution {
    public boolean splitArray(int[] nums) {
        if (nums == null || nums.length < 7) {
            return false;
        }
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 3; i < nums.length - 3; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 1; j < i - 1; j++) {
                if (sums[j] == sums[i] - sums[j + 1]) {
                    if (!set.contains(sums[j])) {
                        set.add(sums[j]);
                    }
                }
            }
            
            for (int k = i + 2; k < nums.length - 1; k++) {
                if (sums[k] - sums[i + 1] == sums[nums.length] - sums[k + 1]) {
                    if (set.contains(sums[k] - sums[i + 1])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

/* 本题是把array 分为四个部分，是通过抽掉三个数来实现的

for loop 先遍历中间那个抽掉的数，从 i = 3 -> nums.size()-3 左右要至少预留三个数

在for loop 内建立一个set，

然后对左半部分处理，for loop 抽掉一个数，看其左右两部分和是否相等，相等的话把该值放入set中
遍历完左半部分后， 对右侧进行for loop 遍历，右侧抽掉一个数，看其左右是否相等，然后再看这个等值是否在set出现，出现过返回true

时间复杂度：O(n^2)
*/