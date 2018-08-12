class NumMatrix {
    int[][] arr, bit;
    int n, m;

    public NumMatrix(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        arr = new int[n][m];
        bit = new int[n + 1][m + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int delta = val - arr[row][col];
        arr[row][col] = val;
        
        for (int i = row + 1; i <= n; i = i + lowbit(i)) {
            for (int j = col + 1; j <= m; j = j + lowbit(j)) {
                bit[i][j] += delta;
            }
        }
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    private int getPrefixSum(int row, int col) {
        int sum = 0;
        
        for (int i = row + 1; i > 0; i = i - lowbit(i)) {
            for (int j = col + 1; j > 0; j = j - lowbit(j)) {
                sum += bit[i][j];
            }
        }
        return sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getPrefixSum(row2, col2) - getPrefixSum(row1 - 1, col2) - getPrefixSum(row2, col1 - 1) + 
                getPrefixSum(row1 - 1, col1 - 1);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */