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
  public List<Integer> preOrder(TreeNode root) {
      //time: O(n), space: O(n)
  /*    List<Integer> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      Deque<TreeNode> stack = new ArrayDeque<>();
      stack.offerLast(root);
    
      while (!stack.isEmpty()) {
          TreeNode node = stack.pollLast();
          results.add(node.key);
          if (node.right != null) {
              stack.offerLast(node.right);
          }
          if (node.left != null) {
              stack.offerLast(node.left);
          }
      }
      return results;  */
    
      //Morris: time: O(n), space: O(1)
      List<Integer> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      TreeNode curr = null;
      
      while (root != null) {
          if (root.left != null) {
              curr = root.left;
              
              while (curr.right != null && curr.right != root) {
                  curr = curr.right;
              }
              if (curr.right == root) {
                  curr.right = null;
                  root = root.right;
              } else {
                  results.add(root.key);
                  curr.right = root;
                  root = root.left;
              }
          } else {
              results.add(root.key);
              root = root.right;
          }
      }
      return results;
  }
}
