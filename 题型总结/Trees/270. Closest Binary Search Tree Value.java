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
  int ans = Integer.MIN_VALUE;
  int diff = Integer.MAX_VALUE;
  public int closest(TreeNode root, int target) {
      //iterative, time: O(height), space: O(1)
      int result = root.key;
      while (root != null) {
          if (root.key == target) {
              return root.key;
          } else {
              if (Math.abs(root.key - target) < Math.abs(result - target)) {
                  result = root.key;
              }
              if (target < root.key) {
                  root = root.left;
              } else {
                  root = root.right;
              }
          }
      }
      return result;
    
      //lowerBound, upperBound
      //time: O(h), space: O(h)
  /*    if (root == null) {
          return -1;
      }
      TreeNode lowerbound = getLowerBound(root, target);
      TreeNode upperbound = getUpperBound(root, target);
      if (lowerbound == null) {
          return upperbound.key;
      }
      if (upperbound == null) {
          return lowerbound.key;
      }
      return Math.abs(target - lowerbound.key) <= Math.abs(target - upperbound.key) ? lowerbound.key : upperbound.key;  */
      //in-order traversal: time: O(n), space:O(n)
   /*   if (root == null) {
          return -1;
      }
      helper(root, target);
      return ans;  */
  }
  
  private TreeNode getUpperBound(TreeNode root, int target) {
      if (root == null) {
          return null;
      }
      if (target > root.key) {
          return getUpperBound(root.right, target);
      }
      TreeNode left = getUpperBound(root.left, target);
      if (left != null) {
          return left;
      }
      return root;
  }
  
  private TreeNode getLowerBound(TreeNode root, int target) {
      if (root == null) {
          return null;
      }
      if (target < root.key) {
          return getLowerBound(root.left, target);
      }
      TreeNode right = getLowerBound(root.right, target);
      if (right != null) {
          return right;
      }
      return root;
  }
  
  private void helper(TreeNode root, int target) {
      if (root == null) {
          return;
      }
      helper(root.left, target);
      if (Math.abs(root.key - target) < diff) {
          ans = root.key;
          diff = Math.abs(root.key - target);
      }
      helper(root.right, target);
  }
}
