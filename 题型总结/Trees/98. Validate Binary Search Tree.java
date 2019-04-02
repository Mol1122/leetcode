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
/*  public boolean isBST(TreeNode root) {
      //preorder, time: O(n), space: O(height)
      if (root == null) {
          return true;
      }
      return isBSTHelper(root, null, null);
  }
  
  private boolean isBSTHelper(TreeNode root, TreeNode minNode, TreeNode maxNode) {
      if (root == null) {
          return true;
      }
      if (minNode != null && minNode.key >= root.key) {
          return false;
      }
      if (maxNode != null && maxNode.key <= root.key) {
          return false;
      }
      return isBSTHelper(root.left, minNode, root) && isBSTHelper(root.right, root, maxNode);
  }  */
  
  //postorder, time: O(n), space: O(height)
  public boolean isBST(TreeNode root) {
      if (root == null) {
          return true;
      }
      ResultType rt = helper(root);
      return rt.isValid;
  }
  
  private ResultType helper(TreeNode root) {
      if (root == null) {
          return new ResultType(true);
      }
      ResultType left = helper(root.left);
      ResultType right = helper(root.right);
      
      if (!left.isValid || !right.isValid) {
          return new ResultType(false);
      }
      if (left.maxNode != null && left.maxNode.key >= root.key) {
          return new ResultType(false);
      }
      if (right.minNode != null && right.minNode.key <= root.key) {
          return new ResultType(false);
      }
      ResultType rt = new ResultType(true);
      rt.minNode = left.minNode == null ? root : left.minNode;
      rt.maxNode = right.maxNode == null ? root : right.maxNode;
      return rt;
  }
}

class ResultType {
    boolean isValid;
    TreeNode minNode, maxNode;
  
    public ResultType(boolean isValid) {
        this.isValid = isValid;
        minNode = maxNode = null;
    }
}


