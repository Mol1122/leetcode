/* Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true */

public class Solution {
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        int start = 0, end = n * m - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int number = matrix[mid / m][mid % m];
            if (target < number) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (matrix[start / m][start % m] == target) {
            return true;
        }
        if (matrix[end / m][end % m] == target) {
            return true;
        }
        return false;
    }
}
/* 算法：把2d array变成1d, 再用二分法. 主要是因为下一行的第一个数比上一行最后一个数大
** 难点：  number = matrix[mid / m][mid % m]; 
time: O(log(n * m)), space: O(1) */