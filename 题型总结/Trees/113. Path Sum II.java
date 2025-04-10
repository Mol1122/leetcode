/**
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */

//Method 1
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        getPath(root, sum, new ArrayList<>(), results);
        return results;
    }
    
    private void getPath(TreeNode root, int sum, List<Integer> temp,
                         List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        temp.add(root.val);
        
        if (sum == 0 && root.left == null && root.right == null) {
            results.add(new ArrayList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }
        getPath(root.left, sum, temp, results);
        getPath(root.right, sum, temp, results);
        temp.remove(temp.size() - 1);
    }
}
//time: O(n), space: O(height)

/* 算法：二叉树上的dfs。求满足条件的所有路径问题，模版问题
** 时间复杂度：O(n) */