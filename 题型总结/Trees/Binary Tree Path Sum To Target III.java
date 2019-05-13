/**
Given a binary tree in which each node contains an integer number. Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), the sum of the numbers on the path is the given target number.

Examples

    5

  /    \

2      11

     /    \

    6     14

  /

 3

If target = 17, There exists a path 11 + 6, the sum of the path is target.

If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.

If target = 10, There does not exist any paths sum of which is target.

If target = 11, There exists a path only containing the node 11.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
 */
public class Solution {
  public boolean exist(TreeNode root, int target) {
      if (root == null) {
          return false;
      }
      List<TreeNode> path = new ArrayList<>();
      return helper(root, path, target);
  }
  
  private boolean helper(TreeNode root, List<TreeNode> path, int target) {
      path.add(root);
    
      int temp = 0;
      for (int i = path.size() - 1; i >= 0; i--) {
          temp += path.get(i).key;
          if (temp == target) {
              return true;
          }
      }
      if (root.left != null && helper(root.left, path, target)) {
          return true;
      }
      if (root.right != null && helper(root.right, path, target)) {
          return true;
      }
    
      path.remove(path.size() - 1);
      return false;
  }
}
//time: O(n^2), space: O(height)
