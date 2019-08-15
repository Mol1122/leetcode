/* Given a binary tree, delete the nodes only if it is 0 and all the nodes on the paths from the node to any leaf nodes are all 0.

In another word, delete the leaf nodes with 0 recursively until there are no such nodes in the tree.

You only need to return the final tree after deletion.

Examples:

          0
        /    \
       0      3
      / \    / \
     0   0   0  7
    /            \
    0             0

     \

      0

After first round, deleting all the leaf nodes with 0, the tree becomes:

          0
        /   \
       0     3
      /     / \
     0     0   7
    /
   0

After second round, deleting all the leaf nodes with 0, the tree becomes:

          0
        /   \
       0     3
      /       \
     0         7

After third round, deleting all the leaf nodes with 0, the tree becomds:

          0
        /   \
       0     3
              \
               7

After another round, deleting all the leaf nodes with 0, the tree becomds:

          0
           \
            3   
             \
              7   

The deletion end at this step since there are no more nodes to delete.

You only need to return the final binary tree after deletion. */

public class Solution {
  public TreeNode deleteZero(TreeNode root) {
    if (root == null) {
      return null;
    }
    return delete(root);
  }

  private TreeNode delete(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode left = delete(root.left);
    TreeNode right = delete(root.right);
    if (left == null) { //容易漏掉
      root.left = null;
    }
    if (right == null) {
      root.right = null;
    }
    if (left == null && right == null && root.key == 0) {
      return null;
    }
    return root;
  }
}
//time: O(n), space: O(height)