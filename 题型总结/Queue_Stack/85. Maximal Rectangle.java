class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] heights = new int[n][m];
        
        //以这一行为bottom,往上数有几个1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0) {
                        heights[i][j] = 1;
                    } else {
                        heights[i][j] = heights[i - 1][j] + 1;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int area = getArea(heights[i]);
            max = Math.max(max, area);
        }
        return max;
    }
    
    private int getArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        
        for (int i = 0; i <= height.length; i++) {
            int curr = i == height.length ? -1 : height[i];
            while (!stack.isEmpty() && curr < height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }
}