/* Given a binary tree, the path cost from the root node to each leaf node is defined to be the number of levels that the leaf is on.

For example, in the following binary tree, the node 5 has its path cost to be 3, and node 8 has its path cost to be 4.

                          1

                      /       \

                   2             3

                /     \             \

             4          5             6

           /   \                      /
 
         7      8                  9

Given a binary tree, try to delete all the nodes that have no paths whose cost is < k that go through it. In the above example, 
node 5 will be deleted  for k = 4. */

public class Solution {
  public TreeNode trimTree(TreeNode root, int k) {
    if (root == null) {
      return null;
    }
    return delete(root, k, 1);
  }

  private TreeNode delete(TreeNode root, int k, int level) {
    if (root == null) {
      return null;
    }
    TreeNode left = delete(root.left, k, level + 1);
    TreeNode right = delete(root.right, k, level + 1);

    if (level < k && left == null && right == null) {
      root.left = null;
      root.right = null;
      return null;
    }
    if (left == null) {
      root.left = null;
    }
    if (right == null) {
      root.right = null;
    }
    return root;
  }
}
//算法：题目写错了。要把从root到leaf, depth < k的nodes都删掉
//time: O(n), space: O(height)