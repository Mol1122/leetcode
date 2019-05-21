
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
    TreeNode prev = null;
    TreeNode head = null;
  public TreeNode toDoubleLinkedList(TreeNode root) {
    inorder(root);
    return head;
  }

  private void inorder(TreeNode root) {
    if (root == null) {
        return;
    }
    inorder(root.left);
    if (prev != null) {
        prev.right = root;
        root.left = prev;
    } else {
        head = root;
    }
    prev = root;
    inorder(root.right);
  }
}

/*public class Solution {
    TreeNode prev = null;
  public TreeNode toDoubleLinkedList(TreeNode root) {
    if (root == null) {
        return null;
    }
    TreeNode dummy = new TreeNode(-1);
    prev = dummy;
    helper(root);

    dummy.right.left = prev;
    prev.right = dummy.right;
    return dummy.right;
  }

  private void helper(TreeNode root) {
    if (root == null) {
        return;
    }
    helper(root.left);
    prev.right = root;
    root.left = prev;
    prev = root;
    helper(root.right);
  }
} */
//time: O(n), space: O(height)