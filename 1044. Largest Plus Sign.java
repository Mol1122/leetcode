public class Solution {
    /**
     * @param N: size of 2D grid
     * @param mines: in the given list
     * @return: the order of the plus sign
     */
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], N);
        }
        for (int[] arr : mines) {
            grid[arr[0]][arr[1]] = 0;
        }
        
        for (int i  = 0; i < N; i++) {
            int l = 0, r = 0, u = 0, d = 0;
            for (int j = 0, k = N - 1; j < N; j++, k--) { //难点：j < N 而不是j<k
                grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1)); //left direction
                grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1)); //right direction
                grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1)); //up direction
                grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1)); //down direction
            }
        }
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result = Math.max(result, grid[i][j]);
            }
        }
        return result;
    }
}

/* 算法：动态规划，遍历每个点看往四个方向最远走多远
** 时间复杂度：O(n^2) 
** 难点：j < N, 因为要遍历所有列 
*/