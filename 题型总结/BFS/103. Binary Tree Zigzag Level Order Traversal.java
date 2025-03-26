/* Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[3],[20,9],[15,7]]
Example 2:

Input: root = [1]
Output: [[1]]
Example 3:

Input: root = []
Output: []          */

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

//Method 2
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        boolean flag = true; 
        
        deque.offerFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                if (flag) {
                    TreeNode node = deque.pollLast();
                    list.add(node.val);
                    if (node.left != null) {
                        deque.offerFirst(node.left);
                    }
                    if (node.right != null) {
                        deque.offerFirst(node.right);
                    }
                } else {
                    TreeNode node = deque.pollFirst();
                    list.add(node.val);
                    if (node.right != null) {
                        deque.offerLast(node.right);
                    }
                    if (node.left != null) {
                        deque.offerLast(node.left);
                    }
                }     
            }
            results.add(list);
            flag = !flag;
        }
        return results;
    }
}
//time: O(n), space: O(n)
//要点是，从一端offer, 那就要从另一端poll. 不然这一层的value和新加进去的value会混淆。一般把右边看成first, 左边看成last