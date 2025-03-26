/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Method 1
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        getLevel(root, 0, results);
        return results;
    }
    
    private void getLevel(TreeNode root, int level, List<List<Integer>> results) {
        if (root == null) {
            return;
        }
        if (level >= results.size()) {
            results.add(0, new ArrayList<>()); //难点
        }
        getLevel(root.left, level + 1, results);
        getLevel(root.right, level + 1, results);
        results.get(results.size() - level - 1).add(root.val);
    }
    
}
/* 算法：用recursion的方法实现bfs, 
** 难点：每当level大于result size的时候，在result的最前面加新的list存值, 因为最底层的要放在最上面
** 时间复杂度：O(n) */

//Method 2
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            results.add(list);     
        }
        Collections.reverse(results);
        return results;
    }
}
//time: O(nlogn), space: O(n)