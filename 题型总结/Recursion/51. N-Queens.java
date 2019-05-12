/* initially positioned at index 0 of the array. A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). Determine the minimum number of jumps you need to jump out of the array.

By jump out, it means you can not stay at the end of the array. Return -1 if you can not do so. */

public class Solution {
  public List<List<Integer>> nqueens(int n) {
      List<List<Integer>> results = new ArrayList<>();
      if (n <= 0) {
          return results;
      }
      dfs(n, new ArrayList<>(), results);
      return results;
  }
  
  private void dfs(int n, List<Integer> temp, List<List<Integer>> results) {
      if (temp.size() == n) {
          results.add(new ArrayList<>(temp));
          return;
      }
      for (int i = 0; i < n; i++) {
          if (isValid(temp, i)) {
              temp.add(i);
              dfs(n, temp, results);
              temp.remove(temp.size() - 1);
          }
      }
  }
  
  private boolean isValid(List<Integer> temp, int col) {
      int row = temp.size();
      for (int i = 0; i < row; i++) {
          if (temp.get(i) == col) {
              return false;
          }
          if (i + temp.get(i) == row + col) {
              return false;
          }
          if (i - temp.get(i) == row - col) {
              return false;
          }
      }
      return true;
  }
}

//time: O(n^n*n)

/* With Obstacles 面试的时候只需要说出思想
class Pair {
  int x, y;
  public Pair(int x, int y) {
      this.x = x;
      this.y = y;
  }
}
*/
public List<List<Pair>> nqueens(int[][] matrix) {
    List<List<Integer>> results = new ArrayList<>();
    if (matrix == null || matrix.length == 0) {
        return results;
    }
    List<Pair[]> rows = preprocess(matrix);
    dfs(rows, matrix, new ArrayList<Pair>(), results);
    return results;
}

private void dfs(List<Pair[]> rows, int[][] matrix, List<Pair> temp, List<List<Pair>> results) {
    if (temp.size() == rows.size()) {
        results.add(new ArrayList<>(temp));
        return;
    }
    int curr_row = temp.size();
    for (int i = rows.get(curr_row)[0].y; i <= rows.get(curr_row)[1].y; i++) {
        if (isValid(matrix, temp, rows.get(curr_row)[0].x, i)) {
            temp.add(new Pair(rows.get(curr_row)[0].x, i));
            dfs(rows, matrix, temp, results);
            temp.remove(temp.size() - 1);
        }
    }
}

private boolean isValid(int[][] matrix, List<Pair> temp, int row, int col) {
    for (Pair p : temp) {
        if (p.y == col) {
            //there is an obstacle between [p.y][col] and [row][col]
            for (int i = p.x + 1; i < row; i++) {
                if (matrix[i][col] == 1) {
                    return true;
                }
            }
            return false;
        }
        if (p.x - p.y == row - col) {
            for (int i = p.x + 1, j = p.y + 1; i < matrix.length && j < col; i++, j++) {
                if (matrix[i][j] == 1) {
                    return true;
                }
            }       
            return false; 
        }
        if (p.x + p.y == row + col) {
            for (int i = p.x - 1, j = p.y - 1; i >= 0 && j > col; i--, j--) {
                if (matrix[i][j] == 1) {
                    return true;
                }
            }       
            return false; 
        }
    }
    return true;
}

private List<Pair[]> preprocess(int[][] matrix) {
    List<Pair[]> results = new ArrayList<>();

    for (int i = 0; i < matrix.length; i++) {
        int start = 0, end = 0;
        while (end < matrix[0].length) {
            while (matrix[i][end] != 1) {
                end++;
            }
            results.add(new Pair[]{new Pair(i, start), new Pair(i, end - 1)});
            start = end + 1;
            end = end + 1;
        }
    }
    return results;
}
