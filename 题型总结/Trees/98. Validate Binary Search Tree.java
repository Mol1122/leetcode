/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public boolean isBST(TreeNode root) {
      //preorder
      //return isBST(root, null, null);
    
      //postorder
      return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  //postorder
  private boolean helper(TreeNode root, int min, int max) {
      if (root == null) {
          return true;
      }
      if (!helper(root.left, min, root.key) || !helper(root.right, root.key, max)) {
          return false;
      }
      if (root.key >= max || root.key <= min) {
          return false;
      }
      return true;
  }
  
  //preorder
  private boolean isBST(TreeNode root, TreeNode minNode, TreeNode maxNode) {
      if (root == null) {
          return true;
      }
      if (minNode != null && root.key <= minNode.key) {
          return false;
      }
      if (maxNode != null && root.key >= maxNode.key) {
          return false;
      }
      return isBST(root.left, minNode, root) && isBST(root.right, root, maxNode);
  }
}

//time: O(n), space: O(height)
