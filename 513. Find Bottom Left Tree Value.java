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
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return bfs(root, 1, new int[]{0, 0});
    }
    
    private int bfs(TreeNode root, int depth, int[] result) {
        if (result[1] < depth) {
            result[0] = root.val;
            result[1] = depth;
        }
        if (root.left != null) {
            bfs(root.left, depth + 1, result);
        }
        if (root.right != null) {
            bfs(root.right, depth + 1, result);
        }
        return result[0];
    }
}

/* 算法：bfs,用result来记录最大深度的最左边的值，只有当depth更新的时候才会更新最左边的值
** 时间复杂度：O(n) */