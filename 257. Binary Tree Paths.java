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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        for (String p : left) {
            result.add(root.val + "->" + p);
        }
        for (String p : right) {
            result.add(root.val + "->" + p);
        }
        if (result.size() == 0) {
            result.add("" + root.val);
        }
        return result;
    }
}

/* 算法：分治型DFS (divide and conqure) */