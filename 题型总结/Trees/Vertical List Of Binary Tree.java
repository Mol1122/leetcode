/**
Given a binary tree, get the vertically representation of it as a list of lists.

The columns should be from left to right, and for each column the nodes should be placed from top to bottom, from left to right.

The following example illustrates vertical order traversal. Input:

            1
          /   \
         2     3
        /  \  / \
       4   5,6   7
                 \     \
                   8     9  

Output:

[[4],         // left most column
 [2],         // 2nd left-most column
 [1, 5, 6], // 3rd left-most column, top->bottom, left->right
 [3, 8],
 [7],
 [9]]      */

public class Solution {
  public List<List<Integer>> verticalPrint(TreeNode root) {
    List<List<Integer>> results = new ArrayList<>();
    if (root == null) {
      return results;
    }
    Map<Integer, List<Integer>> map = new HashMap<>();
    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Integer> cols = new LinkedList<>();
    queue.offer(root);
    cols.offer(0);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      int col = cols.poll();
      map.putIfAbsent(col, new ArrayList<>());
      map.get(col).add(node.key);

      if (node.left != null) {
        queue.offer(node.left);
        cols.offer(col - 1);
      }
      if (node.right != null) {
        queue.offer(node.right);
        cols.offer(col + 1);
      }
    }

    for (int c = Collections.min(map.keySet()); c <= Collections.max(map.keySet()); c++) {
      results.add(map.get(c));
    }
    return results;
  }
}
//time: O(n), space: O(n)