public class Solution {
  public int largest(int[][] matrix) {
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
          return 0;
      }
      int n = matrix.length;
      int m = matrix[0].length;
      int[][] leftUp = getLeftUp(matrix, n, m);
      int[][] rightDown = getRightDown(matrix, n, m);
      
      return merge(leftUp, rightDown, n, m);
  }
  
  private int[][] getLeftUp(int[][] matrix, int n, int m) {
      int[][] left = new int[n][m];
      int[][] up = new int[n][m];
    
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              if (matrix[i][j] == 1) {
                  left[i][j] = getNumber(left, i, j - 1, n, m) + 1;
                  up[i][j] = getNumber(up, i - 1, j, n, m) + 1;
              }
          }
      }
      merge(left, up, n, m);
      return left;
  }
  
  private int[][] getRightDown(int[][] matrix, int n, int m) {
      int[][] right = new int[n][m];
      int[][] down = new int[n][m];
      
      for (int i = n - 1; i >= 0; i--) {
          for (int j = m - 1; j >= 0; j--) {
              if (matrix[i][j] == 1) {
                  right[i][j] = getNumber(right, i, j + 1, n, m) + 1;
                  down[i][j] = getNumber(down, i + 1, j, n, m) + 1;
              }
          }
      }
      merge(right, down, n, m);
      return right;
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
  
  private int getNumber(int[][] array, int x, int y, int n, int m) {
      if (x < 0 || x >= n || y < 0 || y >= m) {
          return 0;
      }
      return array[x][y];
  }
}
//time: O(n^2), space: O(n^2)

/* public class Solution {
  public int largest(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
  			return 0;
  		}
  		//preprocessing
  		int[][] up = getUp(matrix);
  		int[][] down = getDown(matrix);
  		int[][] left = getLeft(matrix);
  		int[][] right = getRight(matrix);

  		int max = 0;
  		for (int i = 0; i < matrix.length; i++) {
  			for (int j = 0; j < matrix[0].length; j++) {
  				int length = Math.min(Math.min(up[i][j], down[i][j]), Math.min(left[i][j], right[i][j]));
  				max = Math.max(max, length);
  			}
  		}
  		return max;
  }
  
  private int[][] getUp(int[][] matrix) {
  		int[][] f = new int[matrix.length][matrix[0].length];

  		for (int i = 0; i < matrix.length; i++) {
  			for (int j = 0; j < matrix[0].length; j++) {
  				if (i == 0) {
  					f[i][j] = matrix[i][j];
  					continue;
  				}
  				if (matrix[i][j] == 0) {
  					f[i][j] = 0;
  				} else {
  					f[i][j] = f[i - 1][j] + 1;
  				}
  			}
  		}
  		return f;
  }

  private int[][] getDown(int[][] matrix) {
  		int[][] f = new int[matrix.length][matrix[0].length];

  		for (int i = matrix.length - 1; i >= 0; i--) {
  			for (int j = 0; j < matrix[0].length; j++) {
  				if (i == matrix.length - 1) {
  					f[i][j] = matrix[i][j];
  					continue;
  				}
  				if (matrix[i][j] == 0) {
  					f[i][j] = 0;
  				} else {
  					f[i][j] = f[i + 1][j] + 1;
  				}
  			}
  		}
  		return f;
  }

  private int[][] getLeft(int[][] matrix) {
  		int[][] f = new int[matrix.length][matrix[0].length];

  		for (int i = 0; i < matrix.length; i++) {
  			for (int j = 0; j < matrix[0].length; j++) {
  				 if (j == 0) {
  				 	f[i][j] = matrix[i][j];
  				 	continue;
  				 }
  				 if (matrix[i][j] == 0) {
  				 	f[i][j] = 0;
  				 } else {
  				 	f[i][j] = f[i][j - 1] + 1;
  				 }
  			}
  		}
  		return f;
  }

  private int[][] getRight(int[][] matrix) {
  		int[][] f = new int[matrix.length][matrix[0].length];

  		for (int i = 0; i < matrix.length; i++) {
  			for (int j = matrix[0].length - 1; j >= 0; j--) {
  				if (j == matrix[0].length - 1) {
  					f[i][j] = matrix[i][j];
  					continue;
  				}
  				if (matrix[i][j] == 0) {
  					f[i][j] = 0;
  				} else {
  					f[i][j] = f[i][j + 1] + 1;
  				}
  			}
  		}
  		return f;
  }
}   */
////time: O(n^2), space: O(n^2)
