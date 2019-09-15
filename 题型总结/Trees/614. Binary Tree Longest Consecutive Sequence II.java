/**
Given a binary tree, find the length(number of nodes) of the longest consecutive sequence(Monotonic and adjacent node values differ by 1) path.
The path could be start and end at any node in the tree

Example
Example 1:

Input:
{1,2,0,3}
Output:
4
Explanation:
    1
   / \
  2   0
 /
3
0-1-2-3
Example 2:

Input:
{3,2,2}
Output:
2
Explanation:
    3
   / \
  2   2
2-3
 */

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    int max = 0;
    public int longestConsecutive2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            int[] arr = {0, 0};
            return arr;
        }
        int[] result = {1, 1}; //increasing, decreasing
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        if (root.left != null) {
            if (root.val == root.left.val + 1) {
                result[0] = Math.max(result[0], left[0] + 1);
            } else if (root.val == root.left.val - 1){
                result[1] = Math.max(result[1], left[1] + 1);
            }
        }
        
        if (root.right != null) {
            if (root.val == root.right.val + 1) {
                result[0] = Math.max(result[0], right[0] + 1);
            } else if (root.val == root.right.val - 1) {
                result[1] = Math.max(result[1], right[1] + 1);
            }
        }
        max = Math.max(max, result[0] + result[1] - 1);
        return result;
    }
}