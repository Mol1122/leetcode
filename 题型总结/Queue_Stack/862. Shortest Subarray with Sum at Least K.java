/* Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

 

Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3
 

Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9 */

class Solution {
    public int shortestSubarray(int[] A, int K) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result = Integer.MAX_VALUE;
        int[] sums = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            sums[i + 1] = sums[i] + A[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i <= A.length; i++) {
            //dequeue从左往右(beginning -> end)就像check
            while (deque.size() > 0 && sums[i] - sums[deque.getFirst()] >= K) { 
                //从大array到小array检查sum是否满足，然后更新                                                                   //因为更新了，不可能比这个更小，就poll走.本来i~j一共j-i+1个数，但是这里是第几个数，所以不用+1
                result = Math.min(result, i - deque.pollFirst()); 
            }
            //如果当前的sum小于之前的sum.说明在deque.getLast()这个index是负数
            //如果后面的sum比之前的sum小，那么sum[i]-prefixSum更容易得到大于等于k.
            //所以知道要维护一个单调递增deque
            while (deque.size() > 0 && sums[i] <= sums[deque.getLast()]) { 
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }
}

/* 不能用同向双指针了，因为会有负数。比如i~j之间刚好满足条件，我想的是i++之后，j不用回退，往下走就行，因为i~j如果刚好满足条件，i+1~j应该就不满足了。但似乎有负数的话j回退能找到更好的解 */
//time: O(n), space: O(n)