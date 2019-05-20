/**
 Given a binary tree and a target sum, determine if the tree has a root-to-leaf path such that 
 adding up all the values along the path equals the given target.

Example:
Given the below binary tree and target = 16,

              5
             / \
            4   8
           /   / \
          1    3  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5-8-3 which sum is 16.
 */
public class Solution {
  public boolean exist(TreeNode root, int target) {
    boolean[] flag = {false};
    getPath(root, target, flag);
    return flag[0];
  }

  private void getPath(TreeNode root, int target, boolean[] flag) {
    if (root == null) {
        return;
    }
    if (root.left == null && root.right == null && target - root.key == 0) {
        flag[0] = true;
        return;
    }
    getPath(root.left, target - root.key, flag);
    getPath(root.right, target - root.key, flag);
  }
}
//算法：用preorder去keep track of prefixSum
//time: O(n), space: O(height)

//NOT RECORMANDED, but the idea is the same as above
/*public class Solution {
  public boolean exist(TreeNode root, int target) {
      if (root == null) {
          return false;
      }
    
      return helper(root, 0, target);
  }
  
  private boolean helper(TreeNode root, int sum, int target) {
      sum += root.key;
      if (sum == target && root.left == null && root.right == null) {
          return true;
      }
      if (root.left != null && helper(root.left, sum, target)) {
          return true;
      }
      if (root.right != null && helper(root.right, sum, target)) {
          return true;
      }
      return false;
  }
}
*/