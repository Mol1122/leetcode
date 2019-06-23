/* Find distance between two given keys of a Binary Tree, no parent pointers are given. Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.

Assumptions:

There are no duplicate keys in the binary tree.
The given two keys are guaranteed to be in the binary tree.
The given two keys are different.
Examples:

    1

   /  \

  2    3

 / \  /  \  

4   5 6   7

       \

         8

distance(4, 5) = 2

distance(4, 6) = 4 */

public class Solution {
  public int distance(TreeNode root, int a, int b) {
    if (root == null) {
      return -1;
    }
    TreeNode lca = getLCA(root, a, b);
    int dist1 = getDistance(lca, a, 0);
    int dist2 = getDistance(lca, b, 0);
    return dist1 + dist2;
  }

  private int getDistance(TreeNode root, int nodeKey, int level) {
    if (root == null) {
      return -1;
    }
    if (root.key == nodeKey) {
      return level;
    }
    int left = getDistance(root.left, nodeKey, level + 1);
    if (left == -1) {
      return getDistance(root.right, nodeKey, level + 1);
    }
    return left;
  }

  private TreeNode getLCA(TreeNode root, int a, int b) {
    if (root == null || root.key == a || root.key == b) {
      return root;
    }
    TreeNode left = getLCA(root.left, a, b);
    TreeNode right = getLCA(root.right, a, b);
    if (left != null && right != null) {
      return root;
    }
    return left == null ? right : left;
  }
}
//time: O(n), space: O(height)