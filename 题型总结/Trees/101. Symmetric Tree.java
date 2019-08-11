/**
Check if a given binary tree is symmetric.

Examples

        5

      /    \

    3        3

  /   \    /   \

1      4  4      1

is symmetric.

        5

      /    \

    3        3

  /   \    /   \

1      4  1      4

is not symmetric.

Corner Cases

What if the binary tree is null? Return true in this case. */

public class Solution {
  public boolean isSymmetric(TreeNode root) {
      if (root == null) {
          return true;
      }
      return helper(root.left, root.right);
  }
  
  private boolean helper(TreeNode left, TreeNode right) {
      if (left == null && right == null) {
          return true;
      } else if (left == null || right == null) {
          return false;
      } else if (left.key != right.key) {
          return false;
      }
      return helper(left.left, right.right) && helper(left.right, right.left);
  }
}
//算法：把value从上往下传递(then从下往上)的题目
/* 时间复杂度：O(n)
** 空间复杂度：O(n) or O(height) 
                (5a, 5b)
                /      \
            (1a, 1b)   (3a, 3b)
            /     \        /   \
        (2a, 2b)  (4a, 4b) ......



*/