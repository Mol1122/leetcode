/**
Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.

Assumptions

There is parent pointer for the nodes in the binary tree

The given two nodes are not guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree) */

public class Solution {
  public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
      int l1 = getLength(one);
      int l2 = getLength(two);
    
      if (l1 < l2) {
          return merge(one, two, l2 - l1);
      } else {
          return merge(two, one, l1 - l2);
      }
  }
  
  private TreeNodeP merge(TreeNodeP shorter, TreeNodeP longer, int diff) {
      while (diff > 0) {
          longer = longer.parent;
          diff--;
      }
      while (shorter != longer) {
          shorter = shorter.parent;
          longer = longer.parent;
      }
      return longer;
  }
  
  private int getLength(TreeNodeP node) {
      int length = 0;
    
      while (node != null) {
          length++;
          node = node.parent;
      }
      return length;
  }
}
//time: O(height), space: O(1)
