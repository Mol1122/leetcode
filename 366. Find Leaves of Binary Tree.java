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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        int max = dfs(root, map);
        for (int i  = 1; i <= max; i++) {
            result.add(map.get(i));
        }

        return result;
    }
    
    private int dfs(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return 0;
        }
        int d = Math.max(dfs(root.left, map), dfs(root.right, map)) + 1;
        map.putIfAbsent(d, new ArrayList<>());
        map.get(d).add(root.val);
        return d;
    }
}