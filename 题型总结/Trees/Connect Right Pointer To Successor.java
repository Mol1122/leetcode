/**
 Connect the node whose right child is NULL to the successor node in in-order sequence.

Examples:

     11

    /  \

  2     29

 /  \  /  \

1   7 15  40

          /

         35

the added edges are:

1.right = 2

7.right = 11

15.right = 29

35.right = 15             */

public class Solution {
  public void connect(TreeNode root) {
    getRight(root);
  }

  private TreeNode getRight(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode left = getRight(root.left);
    if (left != null) {
      left.right = root;
    }
    if (root.right == null) {
      return root;
    }
    return getRight(root.right);
  }
}
//time: O(n), space: O(height)

/* 算法：用分治型dfs去思考，先去到左子树拿到left, 然后返回自己或者最右. 通常题目要求是inorder, 就按照inorder去traverse试试看
** 时间复杂度：O(n)
** 空间复杂度：O(n) */
