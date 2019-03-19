class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int start = 0, end = n * m - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int row = mid / m;
            int col = mid % m;
            if (matrix[row][col] == target) {
                return true;
            } else if (target < matrix[row][col]) {
                end = mid;
            } else {
                start = mid;                                                                      
            }
        }
        int row = start / m;
        int col = start % m;
        if (matrix[row][col] == target) {
            return true;
        }
        row = end / m;
        col = end % m;
        if (matrix[row][col] == target) {
            return true;
        }
        return false;
    }
}