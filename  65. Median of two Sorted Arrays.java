public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        //findKth 时间复杂度：O(log(n + m))
        if (A == null || B == null) {
            return 0.0;
        }
        int n = A.length + B.length;
        if (n % 2 == 1) {
            return findKth(A, 0, B, 0, n / 2 + 1);
        }
        return (findKth(A, 0, B, 0, n / 2) + findKth(A, 0, B, 0, n / 2 + 1)) / 2.0;
    }
    
    private int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }
        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }
        int A_key = A_start + k / 2 - 1 < A.length ?
                    A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length ?
                    B[B_start + k / 2 - 1] : Integer.MAX_VALUE;
        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        }
        return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
    }
}
