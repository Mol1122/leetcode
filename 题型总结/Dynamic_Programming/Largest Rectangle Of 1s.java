public class Solution {
  public int largest(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
        return 0;
    }
    int[][] up = getUp(matrix);
    int max = 0;

    for (int i = 0; i < matrix.length; i++) {
        int area = getArea(up[i]); 
        max = Math.max(max, area);   
    }
    return max;
  }

  public int getArea(int[] heights) {
    Deque<Integer> stack = new ArrayDeque<>();
    int max = 0;

    for (int i = 0; i <= heights.length; i++) {
        int curr = i == heights.length ? -1 : heights[i];
        while (!stack.isEmpty() && curr < heights[stack.peekLast()]) {
            int h = heights[stack.pollLast()];
            int w = stack.isEmpty() ? i : i - stack.peekLast() - 1;
            max = Math.max(max, h * w);
        }
        stack.offerLast(i);
    }
    return max;
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
}
//把matrix压扁后算largest rectangle in histgram
//time: O(n^2), space: O(n^2)