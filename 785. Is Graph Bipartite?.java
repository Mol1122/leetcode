class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == -1 && !validColor(graph, colors, 0, i)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != -1) {
            return colors[node] == color;
        }
        colors[node] = color;
        for (int i : graph[node]) {
            if (!validColor(graph, colors, 1 - color, i)) {
                return false;
            }
        }
        return true;
    }
}

/* 算法：给所有node上色，相邻两个node为不同颜色才对，否则错。-1表示未上色，0是一种颜色，1是另一种颜色。如果当前node没有上色，那么给他上色，并且把它所有的邻居都图成另外一种颜色。如果当前未上色，并钱即将要图的颜色与预期不符（颜色重合），那么返回错

** 时间复杂度：O(n^2) */