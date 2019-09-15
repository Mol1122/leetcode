/**
Given a binary tree, find the subtree with maximum average. Return the root of the subtree.

Example
Example 1

Input：
{1,-5,11,1,2,4,-2}
Output：11
Explanation:
The tree is look like this:
     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2 
The average of subtree of 11 is 4.3333, is the maximun.
Example 2

Input：
{1,-5,11}
Output：11
Explanation:
     1
   /   \
 -5     11
The average of subtree of 1,-5,11 is 2.333,-5,11. So the subtree of 11 is the maximun.
Notice
LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum average.
 */

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    ResultType rt = new ResultType(null, 0, 0);
    public TreeNode findSubtree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return rt.node;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(null, 0, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        int sum = left.sum + right.sum + root.val;
        int size = left.size + right.size + 1;
        
        ResultType curr = new ResultType(root, sum, size);
        if (sum * rt.size >= rt.sum * size) {
            rt = curr;
        }
        return curr;
    }
}

class ResultType {
    TreeNode node;
    int sum, size;
    
    public ResultType(TreeNode node, int sum, int size) {
        this.node = node;
        this.sum = sum;
        this.size = size;
    }
}