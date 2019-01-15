/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
       if (A == null || A.length == 0) {
           return null;
       }
       Stack<TreeNode> stack = new Stack<>();
       
       for (int i = 0; i <= A.length; i++) {
           TreeNode right = i == A.length ? new TreeNode(Integer.MAX_VALUE) :
                            new TreeNode(A[i]);
            while (!stack.isEmpty() && right.val > stack.peek().val) {
                TreeNode nowNode = stack.pop();
                if (stack.isEmpty()) {
                    right.left = nowNode;
                } else {
                    TreeNode left = stack.peek();
                    if (left.val > right.val) {
                        right.left = nowNode;
                    } else {
                        left.right = nowNode;
                    }
                }
            }
            stack.push(right);
       }
       return stack.peek().left;
    }
}

/* 单调递减栈 */