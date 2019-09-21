/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/* Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST. */

class BSTIterator {
    Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.offerLast(root);
            root = root.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        if (hasNext() == false) {
            return Integer.MIN_VALUE;
        }
        TreeNode node = stack.peekLast();
        int val = node.val;
        
        if (node.right == null) {
            node = stack.pollLast();
            while (!stack.isEmpty() && stack.peekLast().right == node) {
                node = stack.pollLast();
            }
        } else {
            node = node.right;
            while (node != null) {
                stack.offerLast(node);
                node = node.left;
            }
        }
        return val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */