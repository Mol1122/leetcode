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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                        queue.offer(node.left);
                    }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (level % 2 == 0) {
                results.add(temp);
            } else {
                Collections.reverse(temp);
                results.add(temp);
            }
            
            level++;
        }
        return results;
    }
}

/* 算法，普通的分层BFS, 只需要在加进arraylist的时候判断下往左还是往有就好
** 时间复杂度：O(n) */