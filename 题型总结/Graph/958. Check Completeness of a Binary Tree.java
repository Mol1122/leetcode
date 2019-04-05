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
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node.left == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(node.left);
            }
            
            if (node.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(node.right);
            }
        }
        return true;
    }
}

/* 时间复杂度：O(V)
** 空间复杂度：O(v) */