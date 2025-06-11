/* Given an circular integer array (the next element of the last element is the first element), 
find a continuous subarray in it, where the sum of numbers is the biggest. Your code should return 
the index of the first number and the index of the last number.

If duplicate answers exist, return any of them.

Example
Example 1:

Input: [3, 1, -100, -3, 4]
Output: [4, 1]
Example 2:

Input: [1,-1]
Output: [0, 0]
Challenge
O(n) time */

//Method 1
public class Solution {
    class Result{
        public int maxSum;
        public int leftIdx, rightIdx;
    }
    
    // coef = 1: find the maximum non-empty subarray
    // coef = -1: find the maximum non-empty subarray
    // A[i] *= coef
    public Result findMax(int[] A, int coef) {
                // Sj        // S{i-1}      // i-1
        int j, nowSum = 0, prevMinSum = 0, prevMinIdx = -1;
        Result res = new Result();
        res.maxSum = Integer.MIN_VALUE;
        for (j = 0; j < A.length; ++j) {
            nowSum += A[j] * coef;
            // Sj- prevMinSum
            if (nowSum - prevMinSum > res.maxSum) {
                res.maxSum = nowSum - prevMinSum;
                res.leftIdx = prevMinIdx; // i - 1
                res.rightIdx = j;
            }
            
            if (nowSum < prevMinSum) {
                prevMinSum = nowSum;
                prevMinIdx = j;
            }
        }
        
        return res;
    }
     
    public List<Integer> continuousSubarraySumII(int[] A) {
        Result max = findMax(A, 1);
        Result min = findMax(A, -1);
        min.maxSum *= -1;
        
        int totSum = 0;
        for (int i = 0; i < A.length; ++i) {
            totSum += A[i];
        }
        
        List<Integer> res = new ArrayList<>();
        if (max.maxSum >= totSum - min.maxSum) {
            res.add(max.leftIdx + 1);
            res.add(max.rightIdx);
        }
        else {
            // special case
            if (min.leftIdx == -1 && min.rightIdx == A.length - 1) {
                res.add(max.leftIdx + 1);
                res.add(max.rightIdx);
            }
            else {
                // use complementary interval for min interval
                // [min.leftIdx+1...min.rightIdx]
                // min.rightIdx + 1 ... len-1, 0, 1, ... min.leftIdx
                res.add(min.rightIdx + 1);
                res.add(min.leftIdx);
            }
        }
        
        return res;
    }
}
/* 分两种情况讨论：

最大数组仍然是中间的某一段
最大数组是去掉了中间的一段之后剩下的部分
第一种情况用传统的最大子数组做法走一遍(参考题解)。第二种做法稍微想一下就可以证明中间被去掉的那一段是整个数组的 minimum subarray。
所以求一遍 minimum subarray 之后，比较两种情况, 取最优解即可

需要特殊考虑 minimum subarray 是取了所有数的情况。 */

//Method 2: double the origianl array + 单调deque
public class Solution {
    /**
     * @param a: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySumII(int[] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int n = nums.length;
        int[] array = new int[2 * n];
        for (int i = 0; i < nums.length; i++) {
            array[i] = nums[i];
            array[i + n] = nums[i];
        }
        int[] sums = new int[2 * n + 1];
        int max = Integer.MIN_VALUE;
        Deque<Integer> deque = new ArrayDeque<>(); //ith num provides of min sum

        for (int i = 1; i <= array.length; i++) {
            sums[i] = sums[i - 1] + array[i - 1];

            while (!deque.isEmpty() && sums[deque.peekLast()] >= sums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i != deque.peekFirst() && sums[i] - sums[deque.peekFirst()] > max) {
                results = Arrays.asList(deque.peekFirst() % n, (i - 1) % n);
                max = sums[i] - sums[deque.peekFirst()];
            }
            if (i == deque.peekFirst() && array[i - 1] > max) {
                results = Arrays.asList((i - 1) % n, (i - 1) % n);
                max = array[i - 1];
            }
            
            while (!deque.isEmpty() && deque.peekFirst() <= i - n) {
                deque.pollFirst();
            }
        }
        return results;
    }
}
//time: O(n), space: O(n)

// [3, 1, -100, -3, 4]
//. --             ---

// [3, 1, -100, -3, 4, 3, 1, -100, -3, 4]
//                  -----


//---------min
//         ****** max
//---------------curr sum


//Method 3: normal case + prefix and surfix sum
public class Solution {
    /**
     * @param a: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySumII(int[] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int n = nums.length;
        //normal case
        int sum = 0, min = 0, minIndex = -1, max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum - min > max) {
                max = sum - min;
                results = Arrays.asList(minIndex + 1, i);
            }
            if (sum < min) {
                min = sum;
                minIndex = i;
            }
        }
        System.out.println("max=" + max);
        //circular case
        int[] rightSum = new int[n]; //sum(i ~ end)
        rightSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i];
        }
        int[] rightMax = new int[n];
        int[] rightMaxIndex = new int[n];
        rightMax[n - 1] = nums[n - 1];
        rightMaxIndex[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (rightMax[i + 1] > rightSum[i]) {
                rightMax[i] = rightMax[i + 1];
                rightMaxIndex[i] = rightMaxIndex[i + 1];
            } else {
                rightMax[i] = rightSum[i];
                rightMaxIndex[i] = i;
            }
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            leftSum += nums[i];

            if (leftSum + rightMax[i + 2] > max) {
                max = leftSum + rightMax[i + 2];
                results = Arrays.asList(rightMaxIndex[i + 2], i);
            }
        }
        return results;
    }
}

// [3, 1, -100, -3, 4]
//.    ------------    normal case
//.---              --- circular case