/* Description
Given an integer arrays, find a contiguous subarray which has the largest sum and length should be between k1 and k2 (include k1 and k2).
Return the largest sum, return 0 if there are fewer than k1 elements in the array.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


The answer is an integer.

Example
Example 1:

Input:
[-2,2,-3,4,-1,2,1,-5,3]
2
4
Output:
6

Explanatipn:
 the contiguous subarray `[4,-1,2,1]` has the largest sum = `6`.
Example 2:

Input:
[1,1,2,3]
5
10
Output:
0 */

//Method 1 暴力解法
public class Solution {
    /**
     * @param nums: an array of integers
     * @param k1: An integer
     * @param k2: An integer
     * @return: the largest sum
     */
    public int maxSubarray5(int[] nums, int k1, int k2) {
        if (nums == null || nums.length < k1) {
            return 0;
        }
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) { //start index of subarray sum(i~j)
            for (int j = i + k1 - 1 ; j <= i + k2 - 1 && j < nums.length; j++) { //end index of subarray sum(i~j)
                max = Math.max(max, sums[j + 1] - sums[i]);
            }
        }
        return max;
    }
}
//time: O(n^2), space: O(n)

//Method 2 滑动窗口
public class Solution {
    /**
     * @param nums: an array of integers
     * @param k1: An integer
     * @param k2: An integer
     * @return: the largest sum
     */
    public int maxSubarray5(int[] nums, int k1, int k2) {
        if (nums == null || nums.length < k1) {
            return 0;
        }
        Deque<Integer> deque = new ArrayDeque<>(); //index
        int max = Integer.MIN_VALUE;
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;

        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];

            if (i >= k1) {
                while (!deque.isEmpty() && sums[deque.peekLast()] >= sums[i - k1]) { //如果之前的数的prefix sum都比i-k1的prefix sum小，那么不可能成为min
                    deque.pollLast();
                }
                deque.offerLast(i - k1); //因为最小要间隔k1个数，所以就存i-k1的prefix sum
                max = Math.max(max, sums[i] - sums[deque.peekFirst()]); //从左边，deque 头开始对比。这个deque.peekFirst()的prefix sum就是间隔k1~k2个数里最大的
            }
            while (!deque.isEmpty() && deque.peekFirst() <= i - k2) { //deque里面的数已经超过间隔k2啦，所以不用在keep prefix sum了
                deque.pollFirst();
            }
        }
        return max;
    }
}

//算法： 还是max subarray的算法，max = currSum - min. 但是min是距离k1～k2之前个数的， 要用deque去记录. 单调递增deque, 因为如果新增的prefix比原来的小， 那么没必要存旧的index了
//time: O(n), space: O(k2-k1)