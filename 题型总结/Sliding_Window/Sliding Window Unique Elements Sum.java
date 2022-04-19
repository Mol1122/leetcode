/* Given an array and a window size that is sliding along the array, find the sum of the count of unique elements 
in each window.

If the window size is larger than the length of array, just regard it as the length of the array (i.e., the window won't slide).
Example
Example1

Input:
[1, 2, 1, 3, 3]
3
Output: 5
Explanation:
First window [1, 2, 1], only 2 is unique, count is 1.
Second window [2, 1, 3], all elements unique, count is 3.
Third window [1, 3, 3], only 1 is unique, count is 1.
sum of count = 1 + 3 + 1 = 5
Example1

Input:
[1, 2, 1, 2, 1]
3
Output: 3 */

public class Solution {
    /**
     * @param nums: the given array
     * @param k: the window size
     * @return: the sum of the count of unique elements in each window
     */
    public int slidingWindowUniqueElementsSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0, count = 0;

        for (int i = 0; i < k && i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) == 2) {
                    count--;
                }
            } else {
                map.put(nums[i], 1);
                count++;
            }
        }
        ans += count;
        for (int i = k; i < nums.length; i++) {
            map.put(nums[i - k], map.get(nums[i - k]) - 1);
            if (map.get(nums[i - k]) == 0) {
                map.remove(nums[i - k]);
                count--;
            }
            if (map.containsKey(nums[i - k]) && map.get(nums[i - k]) == 1) {
                count++;
            }
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) == 2) {
                    count--;
                }
            } else {
                map.put(nums[i], 1);
                count++;
            }
            ans += count;
        }
        return ans;
    }
}
/* 难点：如果在for loop里遍历map找unique element会超时。提速的方法就是想如何利用变量一遍过
time: O(n), space: O(n)
*/