/* Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2 
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns? */

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int result = Integer.MIN_VALUE;
        
        for (int i = 0; i < m; i++) { //遍历左边的col
            int[] sum = new int[n];
            for (int j = i; j < m; j++) { //遍历右边的col
                for (int row = 0; row < n; row++) {
                    sum[row] += matrix[row][j]; //统计i列至j列中每行的元素和
                }
                int currSum = 0, currMax = Integer.MIN_VALUE;
                TreeSet<Integer> sumSet = new TreeSet<>();
                sumSet.add(0); //易漏
                for (int row = 0; row < n; row++) {
                    currSum += sum[row];
                    Integer val = sumSet.ceiling(currSum - k); //难点 currSum - k <= x; currSum - x <= k
                    if (val != null) {
                        currMax = Math.max(currMax, currSum - val);
                    }
                    sumSet.add(currSum);
                }
                result = Math.max(result, currMax);
            }
        }
        return result;
    }
}
//算法：前缀和，2D array的压缩求小部分的matrix sum
//time: O(n * m * (n + nlogn)) = O(n^2 * m (logn)), space: O(n)