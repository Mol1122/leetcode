public static int[] findSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{-1, -1, 0, 0};
        }
        int[] result = new int[]{-1, -1, 0, 0};
        int x = -1, y = -1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (result[0] == -1 && result[1] == -1) {
                        result[0] = i;
                        result[1] = j;
                    } else {
                        x = Math.max(x, i);
                        y = Math.max(y, j);
                    }
                }
            }
        }
        result[2] = x - result[0] + 1;
        result[3] = y - result[1] + 1;
        return result;
}