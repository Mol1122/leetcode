//return the largest edge length of a subsquare
    public int largestLengthSurroundedByOne(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] left = getLeft(matrix);
        int[][] up = getUp(matrix);
        int max = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    for (int length = 2; length <= Math.min(left[i][j], up[i][j]); length++) {
                        if (left[i - length + 1][j] < length) {
                            continue;
                        }
                        if (up[i][j - length + 1] < length) {
                            continue;
                        }

                        max = Math.max(max, length);
                    }
                }
            }
        }
        return max;
    }

    private int[][] getLeft(int[][] matrix) {
        int[][] left = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) {
                    left[i][j] = matrix[i][j] == 1 ? 1 : 0;
                } else {
                    if (matrix[i][j] == 1) {
                        left[i][j] = left[i][j - 1] + 1;
                    }
                }
            }
        }
        return left;
    }

    private int[][] getUp(int[][] matrix) {
        int[][] up = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    up[i][j] = matrix[i][j] == 1 ? 1 : 0;
                } else {
                    if (matrix[i][j] == 1) {
                        up[i][j] = up[i - 1][j] + 1;
                    }
                }
            }
        }
        return up;
    }
    //time: O(n^3), space: O(n^2)