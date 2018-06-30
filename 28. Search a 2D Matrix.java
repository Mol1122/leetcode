public class Solution {
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
                return false;
            }
            
        int n = matrix.length;
        int m = matrix[0].length;
        int start = 0, end = n * m - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int number = matrix[mid / m][mid % m];
            if (number == target) {
                return true;
            } else if (number > target) {
                end = mid;
            } else{
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

/* 算法：把2d array变成1d, 再用二分法
** 难点：  number = matrix[mid / m][mid % m]; */