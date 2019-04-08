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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map, 0);
        
        for (int depth : map.keySet()) {
            results.add(map.get(depth));
        }
        return results;
    }
    
    private void dfs(TreeNode root, Map<Integer, Integer> map, int depth) {
        if (root == null) {
            return;
        }
        map.put(depth, root.val);
        dfs(root.left, map, depth + 1);
        dfs(root.right, map, depth + 1);
    }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */