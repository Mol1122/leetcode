/**
Given a binary tree, get the top view of it. The nodes in the output list should be from left to right. 
A node x belongs to the output if x is the topmost node at its column.

Examples:

     1
   /   \
  2     3
 / \   / \
4  (5,6)  7

the top view is [4, 2, 1, 3, 7]  */

public class Solution {
  public List<Integer> topView(TreeNode root) {
    List<Integer> results = new ArrayList<>();
    if (root == null) {
      return results;
    }
    Map<Integer, TreeNode> col2node = new HashMap<>();
    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Integer> cols = new LinkedList<>();
    queue.offer(root);
    cols.offer(0);
    col2node.put(0, root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      Integer col = cols.poll();

      if (node.left != null) {
        if (!col2node.containsKey(col - 1)) {
          col2node.put(col - 1, node.left);
        }
        queue.offer(node.left);
        cols.offer(col - 1);
      }
      if (node.right != null) {
        if (!col2node.containsKey(col + 1)) {
          col2node.put(col + 1, node.right);
        }
        queue.offer(node.right);
        cols.offer(col + 1);
      }
    }
    for (int c = Collections.min(col2node.keySet()); c <= Collections.max(col2node.keySet()); c++) {
      results.add(col2node.get(c).key);
    }
    return results;
  }
}
//time: O(n), space: O(n)