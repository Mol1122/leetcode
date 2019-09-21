/**
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <--- */
  
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, Integer> depth2val = new HashMap<>();
        dfs(root, depth2val, 0);
        
        for (int depth : depth2val.keySet()) {
            results.add(depth2val.get(depth));
        }
        return results;
    }
    
    private void dfs(TreeNode root, Map<Integer, Integer> depth2val, int depth) {
        if (root == null) {
            return;
        }
        depth2val.put(depth, root.val);
        dfs(root.left, depth2val, depth + 1);
        dfs(root.right, depth2val, depth + 1);
    }
}
//time: O(n), space: O(height)