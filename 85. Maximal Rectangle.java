class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] heights = new int[n][m + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '0') {
                    heights[i][j] = 0;
                } else {
                    heights[i][j] = i == 0 ? 1 : heights[i - 1][j] + 1;
                }
            }
        }
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int temp = findMaxInHist(heights[i]);
            maxArea = Math.max(maxArea, temp);
        }
        return maxArea;
    }
    
    private int findMaxInHist(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int max = 0;
        
        while (i < height.length) {
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) { //ensuring increasing
                stack.push(i++); //stack里面放着的还没有i++
            } else {
                int t = stack.pop();
                max = Math.max(max, height[t] * (stack.isEmpty() ? i : (i - stack.peek() - 1)));
            }
        }
        
        return max;
    }
}

/* 算法：序列型动态规划，但是本题用了stack去处理
** 时间复杂度：O(nm) 
** 难点： int[][] heights = new int[n][m + 1]; “m+1”因为stack要push之后总要前进一个位置，为了防止溢出 */