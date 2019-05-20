/**
 Given a binary tree in which each node contains an integer number. Find the maximum possible sum from any node to any node (the start node and the end node can be the same). 

Assumptions

​The root of the given binary tree is not null
Examples

   -1

  /    \

2      11

     /    \

    6    -14

one example of paths could be -14 -> 11 -> -1 -> 2

another example could be the node 11 itself

The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18

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
      max[0] = Math.max(max[0], left + right + root.key);
    
      return Math.max(Math.max(left, right) + root.key, 0); //这个地方保证了传上去的一定是正数, positive contribution
  }

  /*
  //第二种理解方式
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
        left = left < 0 ? 0 : left; //左边有positive contribution的才加进去
        right = right < 0 ? 0 : right; //右边有positive contribution的才加进去

        max[0] = Math.max(max[0], root.key + left + right);
        return Math.max(left, right) + root.key;
    } 
   */
}
//time: O(n), space: O(height)
