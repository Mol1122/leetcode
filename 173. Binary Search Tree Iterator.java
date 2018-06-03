/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode next = null;

    public BSTIterator(TreeNode root) {
        next = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (next != null) {
            addToStack(next);
            next = null;
        }
        return !stack.isEmpty();
    }
    
    private void addToStack(TreeNode curr) {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext()) {
            return -1;
        }
        TreeNode curr = stack.pop();
        next = curr.right;
        return curr.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

/* 算法：stack一直保存往左走保存值，走到最后向右走 */