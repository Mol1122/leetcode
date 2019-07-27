/* Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false */

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length - 1;
        int col = 0;
        
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                row--;
            }
        }
        return false;
    }
} 

/* 算法：通过值域的二分。mid点就是往上都是小于它，往右都是大于他，那么可知应该从左下角开始遍历
** 这是理论下线，因为最坏情况是 [1, 2, 3, 4], 这跟行数有关
                                              [1, 2, 3, 4]
                                              [1, 2, 3, 4]
** 难点： 如何找到mid点 
    time: O(n + m), space: O(1)
*/