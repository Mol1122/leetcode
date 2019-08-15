/* Check if a given binary tree is completed. A complete binary tree is one in which every level of the binary tree is completely 
filled except possibly the last level. Furthermore, all nodes are as far left as possible.

Examples

        5

      /    \

    3        8

  /   \

1      4

is completed.

        5

      /    \

    3        8

  /   \        \

1      4        11

is not completed.

Corner Cases

What if the binary tree is null? Return true in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4        */

public class Solution {
  public boolean isCompleted(TreeNode root) {
    if (root == null) {
      return true;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean flag = false; //因为上一层一旦有null, 下一层就不能再有任何node, 所以要flag要放在全局

    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();

      if (node.left == null) {
        flag = true;
      } else if (flag) {
        return false;
      } else {
        queue.offer(node.left);
      }

      if (node.right == null) {
        flag = true;
      } else if (flag) {
        return false;
      } else {
        queue.offer(node.right);
      }
    }
    return true;
  }
}
//time: O(n), space: O(n)