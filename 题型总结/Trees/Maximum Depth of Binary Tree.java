/*Description
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Wechat reply the 【97】 get the latest frequent Interview questions . (wechat id : jiuzhang15)

The answer will not exceed 5000

Example
Example 1:

Input:

tree = {}
Output:

0
Explanation:

The height of empty tree is 0.

Example 2:

Input:

tree = {1,2,3,#,#,4,5}
Output:

3
Explanation:

Like this:
1
/ \
2   3
/  \
4    5
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
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}