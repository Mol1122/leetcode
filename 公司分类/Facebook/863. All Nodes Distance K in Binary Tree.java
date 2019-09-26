/* We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  
The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects. */

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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> results = new ArrayList<>();
        if (root == null || target == null) {
            return results;
        }
        Map<TreeNode, TreeNode> node2parent = new HashMap<>();
        dfs(root, null, node2parent);
        
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        queue.offer(target);
        set.add(target);
        
        int step = 0;
        while (!queue.isEmpty() && step < k) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) { //易漏
                    continue;
                }
                if (node.left != null && !set.contains(node.left)) {
                    queue.offer(node.left);
                    set.add(node.left);
                }
                if (node.right != null && !set.contains(node.right)) {
                    queue.offer(node.right);
                    set.add(node.right);
                }
                if (node2parent.containsKey(node)) {
                    TreeNode parent = node2parent.get(node);
                    if (!set.contains(parent)) {
                        queue.offer(parent);
                        set.add(parent);
                    }
                }
            }
            step++;
        }
        if (step != k) {
            return results;
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            results.add(node.val);
        }
        return results;
    }
    
    private void dfs(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> node2parent) {
        if (root != null) {
            node2parent.put(root, parent);
            dfs(root.left, root, node2parent);
            dfs(root.right, root, node2parent);
        }
    }
}