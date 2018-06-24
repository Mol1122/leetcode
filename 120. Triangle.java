class Solution {
    int n;
    int[][] minSum;
    List<List<Integer>> triangle;
    
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 ||
            triangle.get(0) == null || triangle.get(0).size() == 0) {
            return 0;
        }
        n = triangle.size();
        minSum = new int[n][n];
        this.triangle = triangle;
        for (int i = 0; i < n; i++) {
            minSum[i][i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minSum[i][j] = Integer.MAX_VALUE;
            }
        }
        return search(0, 0);
    }
    
    private int search(int x, int y) {
        if (x >= n) { //保证不超行
            return 0;
        }
        if (minSum[x][y] != Integer.MAX_VALUE) {
            return minSum[x][y];
        }
        minSum[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1)) +
            triangle.get(x).get(y);
        return minSum[x][y];
    }
}

/* 算法：记忆化搜索.状态：min, 方程：f[i][j] = min(f[i+1][j], f[i+1][j+1]) + triangle[i][j], 
        初始化：Integer.MAX_VALUE, 答案
** 时间复杂度：O(n)
** 空间复杂度：O(n^2) */