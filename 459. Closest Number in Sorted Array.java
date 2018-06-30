public class Solution {
    /*
     * @param A: an integer array sorted in ascending order
     * @param target: An integer
     * @return: an integer
     */
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int index = firstGreaterOrEqual(A, target);
        if (index == 0) {
            return 0;
        }
        if (index == A.length - 1) {
            return A.length - 1;
        }
        if (Math.abs(A[index] - target) < Math.abs(A[index - 1] - target)) {
            return index;
        } else {
            return index - 1;
        }
     }
     
     private int firstGreaterOrEqual(int[] A, int target) {
         int start = 0, end = A.length - 1;
         while (start +  1 < end) {
             int mid = start + (end - start) / 2;
             if (A[mid] < target) {
                 start = mid;
             } else {
                 end = mid;
             }
         }
         if (A[start] >= target) {
             return start;
         } else if (A[end] >= target) {
             return end;
         } else {
             return A.length - 1;
         }
     }
}

/* 算法：可以想想能不能通过找到一个去向外扩展去找
** 时间复杂度：O(logn) */