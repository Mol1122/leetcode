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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }
    
    private void dfs(TreeNode root, List<Integer> result, int depth) {
        if (root == null) {
            return;
        }
        if (result.size() == depth) { //visit the first element in depth
            result.add(root.val);
        } else {
            result.set(depth, Math.max(result.get(depth), root.val));
        }
        dfs(root.left, result, depth + 1);
        dfs(root.right, result, depth + 1);
    }
}