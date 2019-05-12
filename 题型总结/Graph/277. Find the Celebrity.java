public class Solution {
  public int celebrity(int[][] matrix) {
      if (matrix == null || matrix.length == 0) {
          return -1;
      }
      int ans = 0;
      for (int i = 1; i < matrix.length; i++) {
          if (matrix[ans][i] == 1) {
              ans = i;
          }
      }
      for (int i = 0; i < matrix.length; i++) {
          if (ans != i && matrix[ans][i] == 1) {
              return -1;
          }
          if (ans != i && matrix[i][ans] != 1) {
              return -1;
          }
      }
      return ans;
  }
}
//a valid celebirty graph contains no circle
// i -> j
//time: O(V + E), space: O(1)