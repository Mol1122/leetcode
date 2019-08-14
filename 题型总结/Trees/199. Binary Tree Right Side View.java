/**

Given a Binary Tree, return the right view of it. Right view of a Binary Tree is list of nodes visible when tree is visited from Right side, the order of the nodes in the list should be from top to bottom level of the original tree.

Examples:
           1
        /    \
       2      3
      / \    /  \
     4   5   6  7
    /            \
    9             8

  /  \

 10  11

the right view =  [1, 3, 7, 8, 11]  */

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map, 0);
        
        for (int depth : map.keySet()) {
            results.add(map.get(depth));
        }
        return results;
    }
    
    private void dfs(TreeNode root, Map<Integer, Integer> map, int depth) {
        if (root == null) {
            return;
        }
        map.put(depth, root.val);
        dfs(root.left, map, depth + 1);
        dfs(root.right, map, depth + 1);
    }
}

/* 
算法：算是直上直下的问题
时间复杂度：O(n)
** 空间复杂度：O(n) */