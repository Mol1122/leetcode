/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Method 1
class Solution {
    public TreeNode invertTree(TreeNode root) {
        //non-recursion
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
        
        
        
        //recursion
        // if (root == null) {
        //     return null;
        // }
        // TreeNode left = invertTree(root.left);
        // TreeNode right = invertTree(root.right);
        // root.left = right;
        // root.right = left;
        // return root;
    }
}

//Method 2
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void invertBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        invert(root);
    }

    private TreeNode invert(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invert(root.left);
        TreeNode right = invert(root.right);

        root.right = left;
        root.left = right;
        return root;
    }
}