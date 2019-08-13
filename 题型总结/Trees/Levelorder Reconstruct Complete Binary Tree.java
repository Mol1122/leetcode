/* How to re construct a complete binary tree from its level-order traversal sequence only.

Assumptions:

The given level-order is not null.
Examples:

{1, 2, 3} -->

   1

 /   \

2     3      */

public class Solution {
  int index = 0;
  public TreeNode construct(int[] level) {
    if (level == null || level.length == 0) {
      return null;
    }
    TreeNode root = new TreeNode(level[index++]);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (index < level.length) {
        node.left = new TreeNode(level[index++]);
        queue.offer(node.left);
      }
      if (index < level.length) {
        node.right = new TreeNode(level[index++]);
        queue.offer(node.right);
      }
    }
    return root;
  }
}
//time: O(n), space: O(n)