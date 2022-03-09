/*
Given M nodes in a K-nary tree, find their lowest common ancestor.

Assumptions

- M >= 2.

- There is no parent pointer for the nodes in the K-nary tree.

- The given M nodes are guaranteed to be in the K-nary tree.

Examples

        5

      /   \

     9   12

   / | \      \

  1 2 3     14



The lowest common ancestor of 2, 3, 14 is 5.

The lowest common ancestor of 2, 3, 9 is 9.
*/
/**
* public class KnaryTreeNode {
 *     int key;
 *     List<KnaryTreeNode> children;
 *     public KnaryTreeNode(int key) {
 *         this.key = key;
 *         this.children = new ArrayList<>();
 *     }
 * }
 */
public class Solution {
  public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
    if (root == null) {
      return null;
    }
    Set<KnaryTreeNode> set = new HashSet<>();
    for (KnaryTreeNode node : nodes) {
      set.add(node);
    }
    return getLCA(root, set);
  }

  private KnaryTreeNode getLCA(KnaryTreeNode root, Set<KnaryTreeNode> set) {
    if (root == null || set.contains(root)) {
      return root;
    }
    int count = 0;
    KnaryTreeNode temp = null;

    for (KnaryTreeNode child : root.children) {
      KnaryTreeNode lca = getLCA(child, set);
      if (lca != null) {
        count++;
        if (count >= 2) {
          return root;
        }
        temp = lca;
      }
    }
    return temp;
  }
}
//time: O(n), space: O(height)