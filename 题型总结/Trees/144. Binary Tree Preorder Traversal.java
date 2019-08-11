/**
Implement an iterative, pre-order traversal of a given binary tree, return the list of keys of each node in the tree as it is pre-order traversed.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

Pre-order traversal is [5, 3, 1, 4, 8, 11]

Corner Cases

What if the given binary tree is null? Return an empty list in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4       */
    
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
