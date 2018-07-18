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
        //divide conquer DFS:当前node跟两边儿子的关系就是在两儿子前加上root.val
//         List<String> results = new ArrayList<>();
//         if (root == null) {
//             return results;
//         }
//         List<String> leftPath = binaryTreePaths(root.left);
//         List<String> rightPath = binaryTreePaths(root.right);
        
//         for (String str : leftPath) {
//             results.add(root.val + "->" + str);
//         }
//         for (String str : rightPath) { //leaves
//             results.add(root.val + "->" + str);
//         }
//         if (results.size() == 0) {
//             results.add("" + root.val);
//         }
//         return results;
        
        //traverse:难点在于你如何append string
        List<String> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        helper(root, "", results);
        return results;
    }
    
    private void helper(TreeNode root, String path, List<String> results) {
        if (root == null) {
            return;
        }
    
        if (root.left == null && root.right == null) {
            results.add(path + root.val);
            return;
        }
        if (root.left != null) {
            helper(root.left, path + root.val + "->", results);
        }
        if (root.right != null) {
            helper(root.right, path + root.val + "->", results);
        }
    }
}