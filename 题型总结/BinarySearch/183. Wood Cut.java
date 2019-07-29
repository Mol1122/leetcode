/* Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Example
Example 1

Input:
L = [232, 124, 456]
k = 7
Output: 114
Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.
Example 2

Input:
L = [1, 2, 3]
k = 7
Output: 0
Explanation: It is obvious we can't make it.
Challenge
O(n log Len), where Len is the longest length of the wood.

Notice
You couldn't cut wood into float length.

If you couldn't get >= k pieces, return 0. */

public class Solution {
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }
        int start = 0, end = 0;
        for (int l : L) {
            end = Math.max(l, end);
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(L, mid) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(L, end) >= k) {
            return end;
        }
        return start;
    }
    
    private int count(int[] L, int len) {
        int sum = 0;
        for (int l : L) {
            sum += l / len;
        }
        return sum;
    }
}

/* 算法：按照值域的二分，想想木条可能多长，左边都是能满足的，右边都是不能满足的，找到满足条件的最大值，这就是二分性
** 时间复杂度：O(loglen * N) */