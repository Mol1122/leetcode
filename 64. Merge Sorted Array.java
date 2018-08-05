public class Solution {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1, index = m + n - 1;
      
        
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        while (i >= 0) {
           A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
        
    }
}

/* 算法：跳出惯性思维，从后往前merge */