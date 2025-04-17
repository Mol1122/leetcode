/* Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

Note:

The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
A subtree of root is a tree consisting of root and all of its descendants.
 

Example 1:


Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation: 
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.
Example 2:


Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 */

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
    public int averageOfSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = {0};
        helper(root, count);
        return count[0];
    }

    private Pair helper(TreeNode root, int[] count) {
        if (root == null) {
            return new Pair(0, 0);
        }
        Pair left = helper(root.left, count);
        Pair right = helper(root.right, count);

        int avg = (left.sum + right.sum + root.val) / (left.size + right.size + 1);
        if (avg == root.val) {
            count[0]++;
        }
        return new Pair(left.sum + right.sum + root.val, left.size + right.size + 1);
    }
}

class Pair {
    int sum, size;

    public Pair(int sum, int size) {
        this.sum = sum;
        this.size = size;
    }
}