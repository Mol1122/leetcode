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
    public List<Integer> rightSideView(TreeNode root) {
        HashMap<Integer, Integer> depthToValue = new HashMap<>();
        dfs(depthToValue, root, 1);
        int depth = 1;
        List<Integer> result = new ArrayList<>();
        while (depthToValue.containsKey(depth)) {
            result.add(depthToValue.get(depth));
            depth++;
        }
        return result;
        
    }
    private void dfs(HashMap<Integer, Integer> depthToValue, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        depthToValue.put(depth, root.val);
        dfs(depthToValue, root.left, depth + 1);
        dfs(depthToValue, root.right, depth + 1);
    }   
}

/* 算法：用深度优先及hashmap去记录每一层对应的value，对于同一个depth,后赋值会覆盖前一个的赋值 */