/**
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example
Example 1:

Input:
   1
    \
     3
    / \
   2   4
        \
         5
Output:3
Explanation:
Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:
   2
    \
     3
    / 
   2    
  / 
 1
Output:2
Explanation:
Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.

 */

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = {1};
        getLongest(root, max);
        return max[0];
    }
    
    private int getLongest(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = getLongest(root.left, max);
        int right = getLongest(root.right, max);
        
        int longest = 1;
        if (root.left != null && root.left.val == root.val + 1) {
            longest = Math.max(longest, left + 1);
        }
        if (root.right != null && root.right.val == root.val + 1) {
            longest = Math.max(longest, right + 1);
        }
        max[0] = Math.max(max[0], longest);
        return longest;
    }
}
//time: O(n), space: O(height)