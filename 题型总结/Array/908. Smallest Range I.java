/* Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.

 

Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 0
Explanation: B = [3,3,3] or B = [4,4,4] */

class Solution {
    public int smallestRangeI(int[] A, int K) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : A) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (max - K <= min + K) {
            return 0;
        }
        return max - min - 2 * K;
    }
}
/* 算法：最大值不可能小于max - K，最小值不可能大于min + K
max - K <= min + K，最大值和最小值差距可以为0
max - K > min + K，差距为 max - min - 2 * K

time: O(n), space: O(1)
*/