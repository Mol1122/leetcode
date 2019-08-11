/** mplement an iterative, in-order traversal of a given binary tree, return the list of keys of each node in the tree as it is in-order traversed.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

In-order traversal is [1, 3, 4, 5, 8, 11]

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

    4          */
    
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

  // another method
  public void inOrder(TreeNode root) {
      if (root == null) {
          return;
      }
      Deque<TreeNode> stack = new ArrayDeque<>();
      TreeNode curr = root;

      while (curr != null || !stack.isEmpty()) {
          if (curr != null) {
            stack.offerLast(curr);
            curr = curr.left;
          } else {
            curr = stack.pollLast();
            System.out.println(curr.val);
            curr = curr.right;
          }
      }
  }
}
