//search from the upper right, if 1,
    public static int findLeftMostOne(int[][] matrix) { //time:O(n + m)
        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0, j = m - 1;
        boolean flag = false;

        while (i < n) {
            while (j >= 0 && matrix[i][j] == 1) {
                j--;
                flag = true;
            }
            if (j < 0 && matrix[i][0] == 1) {
                return 0;
            }
            i++;
        }
        if (flag) {
            return j + 1;
        }
        return -1;
}