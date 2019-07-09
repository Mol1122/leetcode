/**
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:

[9,3,15,20,7]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:

[4,9,3,0,1,8,7]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:

[4,9,5,3,0,1,8,2,7]

 */
public class Solution {
  public List<Integer> verticalOrder(TreeNode root) {
    List<Integer> results = new ArrayList<>();
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
      results.addAll(map.get(c));
    }
    return results;
  }
}

class Pair {
  TreeNode node;
  int x, y;

  public Pair(TreeNode node, int x, int y) {
    this.node = node;
    this.x = x;
    this.y = y;
  }
}

//time: O(n), space: O(n)