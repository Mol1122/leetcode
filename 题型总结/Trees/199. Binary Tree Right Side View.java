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

//Method 1
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

//Method 2
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    results.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return results;
    }
}