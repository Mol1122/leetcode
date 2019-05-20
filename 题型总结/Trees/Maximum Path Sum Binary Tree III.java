/**
Given a binary tree in which each node contains an integer number. Determine if there exists a path 
(the path can only be from one node to itself or to any of its descendants), the sum of the numbers on the path is the given target number.

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
 */
//“三部曲”思想
public class Solution {
  public int maxPathSum(TreeNode root) {
      int[] max = {Integer.MIN_VALUE};
      largestPath(root, max);
      return max[0];
  }
  
  private int largestPath(TreeNode root, int[] max) {
      if (root == null) {
          return 0;
      }
      int left = largestPath(root.left, max);
      int right = largestPath(root.right, max);
      
      max[0] = Math.max(max[0], Math.max(left, right) + root.key);
    
      return Math.max(Math.max(left, right) + root.key, 0);
  }
}
//time: O(n), space: O(height)

//get max subarray思想
public class Solution {
  public int maxPathSum(TreeNode root) {
    int[] max = {Integer.MIN_VALUE};
    largestSum(root, max, 0);
    return max[0];
  }

  private void largestSum(TreeNode root, int[] max, int sum) {
    if (root == null) {
        return;
    }
    if (sum > 0) {
        sum = sum + root.key;
    } else {
        sum = root.key;
    }
    max[0] = Math.max(max[0], sum);
    //preorder traversal
    largestSum(root.left, max, sum);
    largestSum(root.right, max, sum);
  }
}
