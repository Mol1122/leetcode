/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class BSTIterator {
    Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        while (root != null) {
            stack.offerLast(root);
            root = root.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
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

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */