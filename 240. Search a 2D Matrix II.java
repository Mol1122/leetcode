class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0){
            return false;
        }
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else{
                col++;
            }
        }
        return false;
    }
} 

/* 算法：通过值域的二分。mid点就是往上都是小于它，往右都是大于他，那么可知应该从左下角开始遍历
** 时间复杂度：O(n + m) 这是理论下线，因为最坏情况是 [1, 2, 3, 4], 这跟行数有关
                                              [1, 2, 3, 4]
                                              [1, 2, 3, 4]
** 难点： 如何找到mid点 */