/**
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,

              5
             / \
            1   5
           / \   \
          5   5   5
return 4.                 */

public class Solution {
  public int countUnivalSubtrees(TreeNode root) {
    int[] count = {0};
    getUni(root, count);
    return count[0];
  }

  private boolean getUni(TreeNode root, int[] count) {
    if (root == null) {
      return true;
    }
    boolean left = getUni(root.left, count);
    boolean right = getUni(root.right, count);

    if (!left || !right) {
      return false;
    }
    if (root.left != null && root.left.key != root.key) {
      return false;
    }
    if (root.right != null && root.right.key != root.key) {
      return false;
    }
    count[0]++;
    return true;
  }
}
//time: O(n), space: O(height) 


/*class Solution {
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return count;
    }
    
    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = helper(root.left);
        boolean right = helper(root.right);
        
        if (left && right) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }
            count++;
            return true;
        }
        return false;
    }
}  */
/* 时间复杂度：O(n)
** 空间复杂度：O(n) */