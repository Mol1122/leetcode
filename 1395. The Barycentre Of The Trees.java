public class Solution {
    /**
     * @param x: The vertexes of the edges
     * @param y: The vertexes of the edges
     * @return: Return the index of barycentre
     */
    int ansNode = 0;
    int ansSize;
    public int getBarycentre(int[] x, int[] y) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < x.length + 2; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < x.length; i++) { //注意index out of bound
            graph.get(x[i]).add(y[i]);
            graph.get(y[i]).add(x[i]);
        }
        int[] dp = new int[x.length + 2];
        ansSize = x.length + 2;
        ansNode = 0;
        dfs(1, 0, x.length + 1, dp, graph);
        return ansNode;
    }
    
    private void dfs(int x, int f, int n, int[] dp, List<List<Integer>> graph) {
        dp[x] = 1;
        int maxSubtree = 0;
        
        for (int i = 0; i < graph.get(x).size(); i++) {
            int y = graph.get(x).get(i);
            if (y == f) {
                continue;
            }
            dfs(y, x, n, dp, graph);
            dp[x] +=  dp[y];
            maxSubtree = Math.max(maxSubtree, dp[y]);
        }
        maxSubtree = Math.max(maxSubtree, n - dp[x]);
        if (maxSubtree < ansSize || (maxSubtree == ansSize && x < ansNode)) {
            ansNode = x;
            ansSize = maxSubtree;
        }
    }
}

/* 算法：树型DP

    dp[i] 代表以 i 为根的子树的结点个数，dp[i] = sum(dp[j]) + 1。
    则以 i 为根的子树的最大结点个数为 max(max(dp[j]), n - dp[i])。
    在 DFS 的过程中维护答案即可。

    难点：dp[x] = 1,因为要把当前的node算进去 */