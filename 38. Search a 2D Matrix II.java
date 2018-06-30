public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
        	return 0;
        }
        int r = matrix.length - 1;
        int c = 0;
        int ans = 0;
        while (r >= 0 && c < matrix[0].length) {
        	if (matrix[r][c] == target) {
        		r--;
        		c++;
        		ans++;
        		continue;
        	}
        	if (target < matrix[r][c]) {
        		r--;
        	} else {
        		c++;
        	}
        }
        return ans;
    }
}