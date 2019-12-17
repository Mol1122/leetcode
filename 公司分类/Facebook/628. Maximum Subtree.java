/* Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.

Example
Example 1:

Input:
{1,-5,2,0,3,-4,-5}
Output:3
Explanation:
The tree is look like this:
     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
The sum of subtree 3 (only one node) is the maximum. So we return 3.
Example 2:

Input:
{1}
Output:1
Explanation:
The tree is look like this:
   1
There is one and only one subtree in the tree. So we return 1. */

public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    public TreeNode return_node = null;
	public int max = Integer.MIN_VALUE;
	// Maximum Subtree
	public TreeNode findSubtree(TreeNode root) {
        if (root == null) {
        	return null;
        }
        
        findSubtreeHelper(root);
        return return_node;
    }
	private int findSubtreeHelper(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left_sum = findSubtreeHelper(node.left);
		int right_sum = findSubtreeHelper(node.right);
		
		if (return_node == null || node.val + left_sum + right_sum > max) {
			max = node.val + left_sum + right_sum;
			return_node = node;
		}
		return node.val + left_sum + right_sum;
	}
}