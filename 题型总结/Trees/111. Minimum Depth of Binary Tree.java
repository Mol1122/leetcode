/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMinDFS(root);
    }
    
    private int getMinDFS(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right ==null) { //不能漏，因为这个是判断leaf
            return 1;
        }
        return Math.min(getMinDFS(root.left), getMinDFS(root.right)) + 1;
    }
}

/* 算法：分治型dfs
** 时间复杂度：O(n)
** 空间复杂度：O(n)
** 难点：不能直接判断root == null的时候return = 0，因为如果只有一个path的时候，不能算height = 0。 为了避免走向没有leaf的那边，当root == null的时候复制Integer.MAX_VALUE */