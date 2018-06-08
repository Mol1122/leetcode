class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return new int[][]{};
        }
        int n = A.length; //A的行
        int m = B[0].length; //B的列
        int t = A[0].length;
        List<List<Integer>> col = new ArrayList<>();
        int[][] C = new int[A.length][B[0].length]; 
        
        for (int i = 0; i < t; i++) {
            col.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                if (B[i][j] != 0) {
                    col.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] == 0) {
                    continue;
                }
                for (int p = 0; p < col.get(k).size(); p++) { //遍历B的某一列，所以要一行行get size
                    int j = col.get(k).get(p);
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}