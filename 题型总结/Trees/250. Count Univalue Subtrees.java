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

//Method 1
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

//Method 2
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = {0};
        helper(root, count);
        return count[0];
    }

    private Pair helper(TreeNode root, int[] count) {
        if (root == null) {
            return new Pair(0, true);
        }
        Pair left = helper(root.left, count);
        Pair right = helper(root.right, count);

        if (!left.hasSameValue || root.left != null && root.val != left.val) {
            return new Pair(root.val, false);
        }
        if (!right.hasSameValue || root.right != null && root.val != right.val) {
            return new Pair(root.val, false);
        }
        count[0]++;
        return new Pair(root.val, true);
    }
}

class Pair {
    int val;
    boolean hasSameValue;

    public Pair(int val, boolean hasSameValue) {
        this.val = val;
        this.hasSameValue = hasSameValue;
    }
}


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