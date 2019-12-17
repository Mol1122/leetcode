/* Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in 
diagonal order as shown in the below image.

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9] */

class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] results = new int[n * m];
        boolean flag = true;
        int i = 0, j = 0;
        
        int index = 0;
        while (i <= n - 1 && j <= m - 1) {
            if (flag) {
                while (i > 0 && j < m - 1) {
                    results[index++] = matrix[i][j];
                    i--;
                    j++;
                }
                results[index++] = matrix[i][j];
                if (j != m - 1) {
                    j++;
                } else {
                    i++;
                }
            } else {
                while (i < n - 1 && j > 0) {
                    results[index++] = matrix[i][j];
                    i++;
                    j--;
                }
                results[index++] = matrix[i][j];
                if (i != n - 1) {
                    i++;
                } else {
                    j++;
                }
            }
            flag = !flag;
        }
        return results;
    }
}
//time: O(n * m), space: O(1)