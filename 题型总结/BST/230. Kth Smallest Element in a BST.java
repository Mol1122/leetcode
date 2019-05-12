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
    int index, kth;
    public int kthSmallest(TreeNode root, int k) {
        //time: O(k + h), space: O(h)
        // if (root == null) {
        //     return -1;
        // }
        // helper(root, k);
        // return kth;
        
        //算法思想来自于quickSelect,如果有多次更改的话，每个孩子可以只用查询一次，会比较快
        //time: O(n), space: O(n)
        if (root == null) {
            return -1;
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        getChildren(root, map);
        
        return getKth(root, k, map);
    }
    
    private int getKth(TreeNode root, int k, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        int left = root.left == null ? 0 : map.get(root.left);
        if (k <= left) {
            return getKth(root.left, k, map);
        }
        if (left + 1 == k) {
            return root.val;
        }
        return getKth(root.right, k - left - 1, map);
    }
    
    private int getChildren(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        int left = getChildren(root.left, map);
        int right = getChildren(root.right, map);
        map.put(root, left + right + 1);
        return left + right + 1;
    }
    
    private void helper(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        helper(root.left, k);
        index++;
        if (index == k) {
            kth = root.val;
        }
        if (index < k) {
            helper(root.right, k);
        }
    }
}

