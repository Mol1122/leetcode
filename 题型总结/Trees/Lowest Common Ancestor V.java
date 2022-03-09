/*
Given two nodes in a K-nary tree, find their lowest common ancestor.

Assumptions

-There is no parent pointer for the nodes in the K-nary tree.

-The given two nodes are guaranteed to be in the K-nary tree.

Examples



        5

      /   \

     9   12

   / | \      \

 1 2   3      14



The lowest common ancestor of 2 and 14 is 5.

The lowest common ancestor of 2 and 9 is 9.

 */

public class Solution {
  public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode node1, KnaryTreeNode node2) {
    if (root == null || node1 == null || node2 == null) {
      return null;
    }
    return getLCA(root, node1, node2);
  }

  private KnaryTreeNode getLCA(KnaryTreeNode root, KnaryTreeNode node1, KnaryTreeNode node2) {
    if (root == null || root == node1 || root == node2) {
      return root;
    }
    int count = 0;
    KnaryTreeNode temp = null;

    for (KnaryTreeNode child : root.children) {
      KnaryTreeNode lca = getLCA(child, node1, node2);
      if (lca != null) {
        count++;
        if (count == 2) {
          return root;
        }
        temp = lca;
      }
    }
    return temp;
  }
}
//time: O(n), space: O(height)