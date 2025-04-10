/* Given a root node of binary search tree, a lower bound and an upper bound, remove nodes that are outside of [lower, upper]. Return the root node after trimming the binary search tree. The root returned may not the same as the given root node. Return root as null if entire tree has been trimmed.

Example:

Input: root = [5,2,8,1,3,6,9]   lower = 2, upper = 4

                  5       

                /    \

              2       8

            /   \    /  \

           1   3   6    9

Output: [2, null, 3]

                 2

                    \

                      3 */



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        TreeNode left = trimBST(root.left, low, high);
        TreeNode right = trimBST(root.right, low, high);

        root.left = left;
        root.right = right;
        if (root.val < low) {
            return right;
        } else if (root.val > high) {
            return left;
        }
        
        return root;
    }
}
//time: O(n), space: O(height)              