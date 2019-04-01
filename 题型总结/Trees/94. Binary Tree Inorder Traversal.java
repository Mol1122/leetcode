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
  public List<Integer> inOrder(TreeNode root) {
    //time: O(n), space: O(n)
/*      List<Integer> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      Deque<TreeNode> stack = new ArrayDeque<>();
      while (root != null) {
          stack.offerLast(root);
          root = root.left;
      }
      while (!stack.isEmpty()) {
          TreeNode node = stack.peekLast();
          results.add(node.key);
        
          if (node.right == null) {
              node = stack.pollLast();
              while (!stack.isEmpty() && stack.peekLast().right == node) {
                  node = stack.pollLast();
              }
          } else {
              node = node.right;
              while (node != null) {
                  stack.offerLast(node);
                  node = node.left;
              }
          }
      }
      return results;    */
    
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
                  results.add(root.key);
                  curr.right = null;
                  root = root.right;
              } else {
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
