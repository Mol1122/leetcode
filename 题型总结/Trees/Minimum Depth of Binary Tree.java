/*
Description
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Wechat reply the 【155】 get the latest frequent Interview questions . (wechat id : jiuzhang15)

Example
Example 1:

Input: {}
Output: 0
Example 2:

Input:  {1,#,2,3}
Output: 3   
Explanation:
    1
     \ 
      2
     /
    3    
it will be serialized {1,#,2,3}
Example 3:

Input:  {1,2,3,#,#,4,5}
Output: 2   
Explanation: 
      1
     / \ 
    2   3
       / \
      4   5  
it will be serialized {1,2,3,#,#,4,5}
*/
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getDepth(root);
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE; //avoid taking value from right if root.left != null
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.min(left, right) + 1;
    }
}