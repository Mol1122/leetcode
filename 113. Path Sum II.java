/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Path Sum II
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        dfs(root, sum, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        sum -= root.val;
        
        if (root.left == null && root.right == null && sum == 0) {
            results.add(new ArrayList<>(path));
            path.remove(path.size() - 1); //难点
            return;
        }
        dfs(root.left, sum, path, results);
        dfs(root.right, sum, path, results);
        path.remove(path.size() - 1);
    }
}

/* 算法：二叉树上的dfs。求满足条件的所有路径问题，模版问题
** 时间复杂度：O(n) */