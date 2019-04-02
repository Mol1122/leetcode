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
  public List<Integer> getRange(TreeNode root, int min, int max) {
      List<Integer> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      inorder(root, min, max, results);
      return results;
  }
  
  private void inorder(TreeNode root, int min, int max, List<Integer> results) {
      if (root == null) {
          return;
      }
      if (root.key > min) {
          inorder(root.left, min, max, results);
      }
      if (root.key >= min && root.key <= max) {
          results.add(root.key);
      }
      if (root.key < max) {
          inorder(root.right, min, max, results);
      }
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(height) */
