/* Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: [] */

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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Map<Integer, List<Integer>> hash = new HashMap<>();
        Queue<Integer> qCol = new LinkedList<>();
        Queue<TreeNode> qNode = new LinkedList<>();
        qCol.offer(0);
        qNode.offer(root);
        
        while (!qCol.isEmpty()) {
            int c = qCol.poll();
            TreeNode node = qNode.poll();
            hash.putIfAbsent(c, new ArrayList<>());
            hash.get(c).add(node.val);
            
            if (node.left != null) {
                qCol.offer(c - 1);
                qNode.offer(node.left);
            }
            if (node.right != null) {
                qCol.offer(c + 1);
                qNode.offer(node.right);
            }
        }
        for (int i = Collections.min(hash.keySet()); i <= Collections.max(hash.keySet()); i++) {
            result.add(hash.get(i));
        }
        return result;
    } 
}

/* 算法：碰到这种 Tree 的不规则访问问题，用hash来对应。 用queue来保存访问顺序
** 语法难点： Collections.min(hash.set()) 找到hash里面最小值 
** 时间复杂度： O(n) */