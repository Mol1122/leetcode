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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        
        if (root == null) {
            return results;
        }
        results.addAll(postorderTraversal(root.left));
        results.addAll(postorderTraversal(root.right));
        results.add(root.val);
        
        return results;
    }
}