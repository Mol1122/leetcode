public class Solution {
    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] result = new int[2][2];
        int[][] sum = new int[n][m];
        sum[0][0] = matrix[0][0];
        
        for (int i = 1; i < n; i++) {
            sum[i][0] = sum[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < m; j++) {
            sum[0][j] = sum[0][j - 1] + matrix[0][j];
        }
        //compute the sums
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    result[0][0] = i;
                    result[0][1] = j;
                    result[1][0] = i;
                    result[1][1] = j;
                    return result;
                }
                sum[i][j] = matrix[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                if (sum[i][j] == 0) {
                    result[0][0] = 0;
                    result[0][1] = 0;
                    result[1][0] = i;
                    result[1][1] = j;
                    return result;
                }
            }
        }
        
        if (n == 1) {
            Map<Integer, Integer> map = new HashMap<>(); //sum, col
            for (int j = 0; j < m; j++) {
                if (map.containsKey(sum[0][j])) {
                    result[0][0] = 0;
                    result[0][1] = map.get(sum[0][j]) + 1;
                    result[1][0] = 0;
                    result[1][1] = j;
                    return result;
                } else {
                    map.put(sum[0][j], j);
                }
            }
        }
        if (m == 1) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (map.containsKey(sum[i][0])) {
                    result[0][0] = map.get(sum[i][0]) + 1;
                    result[0][1] = 0;
                    result[1][0] = i;
                    result[1][1] = 0;
                    return result;
                } else {
                    map.put(sum[i][0], i);
                }
            }
        }
        //从上往下
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Map<Integer, Point> map = new HashMap<>();
                for (int k = 0; k < m; k++) { //从左往右看能否找到同样的submatrix, 跟subarray那题类似
                    int diff = sum[j][k] - sum[i][k];
                    if (map.containsKey(diff)) {
                        result[0][0] = map.get(diff).x + 1;
                        result[0][1] = map.get(diff).y + 1;
                        result[1][0] = j;
                        result[1][1] = k;
                        return result;
                    } else {
                        map.put(diff, new Point(i, k));
                    }
                }
            }
        }
        return result;
    }
}

class Point {
    int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}