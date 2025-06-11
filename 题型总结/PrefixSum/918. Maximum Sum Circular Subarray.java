/* Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

 

Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.
Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
Example 3:

Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2. */

//Method 1: prefix sum + 单调队列
//trick: 把数组扩展两倍
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] array = new int[2 * n];

        for (int i = 0; i < nums.length; i++) {
            array[i] = nums[i];
            array[i + n] = nums[i];
        }
        Deque<Integer> deque = new ArrayDeque<>(); //store the index of previous array that has min sum
        int[] sums = new int[2 * n + 1];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= array.length; i++) {
            sums[i] = sums[i - 1] + array[i - 1];

            while (!deque.isEmpty() && sums[deque.peekLast()] >= sums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i != deque.peekFirst()) {
                max = Math.max(max, sums[i] - sums[deque.peekFirst()]);
            } else {
                max = Math.max(max, array[i - 1]);
            }
            while (!deque.isEmpty() && deque.peekFirst() <= i - n) {
                deque.pollFirst();
            }
        }
        return max;
    }
}
// [1,-2,3,-2, 1,-2,3,-2]
//---------min
//         *************
//----------------------curr sum



//Method 2: normal base + prefix sum and suffix sum(两段式)
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //normal case
        int sum = 0, min = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
        }
        
        //circular case
        //get surfix sum
        int[] rightSum = new int[n];
        rightSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i];
        }
        int[] rightMax = new int[n]; //rightMax[i] = what's the max sum ending at index i from right to left
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], rightSum[i]);
        }

        int leftSum = 0;
        for (int i = 0; i < n - 2; i++) {
            leftSum += nums[i];
            max = Math.max(max, leftSum + rightMax[i + 2]);
        }
        return max;
    }
}
// [1,-2,3,-2]
//    -----        normal case
// --      ---     circular case


//----min
//     *********
// -------------curr sum

//Method 3: normal case + minimum array
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, currMax = 0, currMin = 0, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum - currMin);
            currMin = Math.min(currMin, sum);

            min = Math.min(min, sum - currMax);
            currMax = Math.max(currMax, sum);
        }
        //corner case. all negative nums, toatal sum = min
        return max > 0 ? Math.max(max, sum - min) : max;
    }
}

// [1,-2,3,-2]
//.   ----- normal case
//. --     --- circular case. 
//.   ***** we want to minimize this subarray



//--------min
//        *******
//--------------- curr sum