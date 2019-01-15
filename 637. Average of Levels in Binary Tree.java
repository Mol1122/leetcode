/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, List<Integer>> depth2val = new HashMap<>();
        dfs(root, depth2val, 0);
        
        for (int depth : depth2val.keySet()) {
            int size = depth2val.get(depth).size();
            double sum = 0;
            for (int num : depth2val.get(depth)) {
                sum += num;
            }
            results.add(sum / size);
        }
        return results;
    }
    
    private void dfs(TreeNode root, Map<Integer, List<Integer>> depth2val, int depth) {
        if (root == null) {
            return;
        }
        depth2val.putIfAbsent(depth, new ArrayList<>());
        depth2val.get(depth).add(root.val);
        
        dfs(root.left, depth2val, depth + 1);
        dfs(root.right, depth2val, depth + 1);
    }
}