/**
Implement an iterative, post-order traversal of a given binary tree, return the list of keys of each node in the tree as it is post-order traversed.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

Post-order traversal is [1, 4, 3, 11, 8, 5]

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

    4      */
    
public class Solution {
  public List<Integer> postOrder(TreeNode root) {
      //time: O(n), space: O(n)
 /*     List<Integer> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      Deque<TreeNode> stack = new ArrayDeque<>();
      TreeNode prev = null;
      TreeNode curr = root;
    
      stack.offerLast(root);
      while (!stack.isEmpty()) {
          curr = stack.peekLast();
          if (prev == null || prev.left == curr || prev.right == curr) {
              if (curr.left != null) {
                  stack.offerLast(curr.left);
              } else if (curr.right != null) {
                  stack.offerLast(curr.right);
              }
          } else if (curr.left == prev) {
              if (curr.right != null) {
                  stack.offerLast(curr.right);
              }
          } else {
              results.add(curr.key);
              stack.pollLast();
          }
          prev = curr;
      }
      return results;  */
      
      //Morris: time: O(n), space: O(1)
      List<Integer> results = new ArrayList<>();
      TreeNode curr = null;
    
      while (root != null) {
          if (root.right != null) {
              curr = root.right;
            
              while (curr.left != null && curr.left != root) {
                  curr = curr.left;
              }
              if (curr.left == root) {
                  curr.left = null;
                  root = root.left;
              } else {
                  results.add(root.key);
                  curr.left = root;
                  root = root.right;
              }
          } else {
              results.add(root.key);
              root = root.left;
          }
      }
      Collections.reverse(results);
      return results;
  }
}
