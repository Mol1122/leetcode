public class Solution {
  public int largest(int[][] matrix) {
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
          return 0;
      }
      int n = matrix.length;
      int m = matrix[0].length;
      int[][] up = getUp(matrix, n, m);
      int[][] down = getDown(matrix, n, m);
    
      return merge(up, down, n, m);
  }
  
  private int getNumber(int[][] array, int x, int y, int n, int m) {
      if (x < 0 || x >= n || y < 0 || y >= m) {
          return 0;
      }
      return array[x][y];
  }
  
  private int merge(int[][] one, int[][] two, int n, int m) {
      int max = 0;
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              one[i][j] = Math.min(one[i][j], two[i][j]);
              max = Math.max(max, one[i][j]);
          }
      }
      return max;
  }
  
  private int[][] getUp(int[][] matrix, int n, int m) {
      int[][] leftUp = new int[n][m];
      int[][] rightUp = new int[n][m];
    
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              if (matrix[i][j] == 1) {
                  leftUp[i][j] = getNumber(leftUp, i - 1, j - 1, n, m) + 1;
                  rightUp[i][j] = getNumber(rightUp, i - 1, j + 1, n, m) + 1;
              }
          }
      }
      merge(leftUp, rightUp, n, m);
      return leftUp;
  }
  
  private int[][] getDown(int[][] matrix, int n, int m) {
      int[][] leftDown = new int[n][m];
      int[][] rightDown = new int[n][m];
    
      for (int i = n - 1; i >= 0; i--) {
          for (int j = m - 1; j >= 0; j--) {
              if (matrix[i][j] == 1) {
                  leftDown[i][j] = getNumber(leftDown, i + 1, j - 1, n, m) + 1;
                  rightDown[i][j] = getNumber(rightDown, i + 1, j + 1, n, m) + 1;
              }
          }
      }
      merge(leftDown, rightDown, n, m);
      return leftDown;
  }
}
//time: O(n^2), space: O(n^2)

/* public class Solution {
   public int largest(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] dir1 = getDirect1(matrix);
        int[][] dir2 = getDirect2(matrix);
        int[][] dir3 = getDirect3(matrix);
        int[][] dir4 = getDirect4(matrix);

        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int length = Math.min(Math.min(dir1[i][j], dir2[i][j]), Math.min(dir3[i][j], dir4[i][j]));
                max = Math.max(max, length);
            }
        }
        return max;
    }

    private int[][] getDirect1(int[][] matrix) {
        int[][] f = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0) {
                    f[i][j] = matrix[i][j];
                    continue;
                }
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i - 1][j - 1] + 1;
                }
            }
        }
        return f;
    }

    private int[][] getDirect2(int[][] matrix) {
        int[][] f = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (i == 0 || j == matrix[0].length - 1) {
                    f[i][j] = matrix[i][j];
                    continue;
                }
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i - 1][j + 1] + 1;
                }
            }
        }
        return f;
    }

    private int[][] getDirect3(int[][] matrix) {
        int[][] f = new int[matrix.length][matrix[0].length];

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == matrix.length - 1 || j == 0) {
                    f[i][j] = matrix[i][j];
                    continue;
                }
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i + 1][j - 1] + 1;
                }
            }
        }
        return f;
    }

    private int[][] getDirect4(int[][] matrix) {
        int[][] f = new int[matrix.length][matrix[0].length];

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (i == matrix.length - 1 || j == matrix[0].length - 1) {
                    f[i][j] = matrix[i][j];
                    continue;
                }
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i + 1][j + 1] + 1;
                }
            }
        }
        return f;
    }
}
  */

//time: O(n^2), space: O(n^2)
