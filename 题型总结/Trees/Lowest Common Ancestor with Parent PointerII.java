/**
 * public class TreeNodeP {
 *   public int key;
 *   public TreeNodeP left;
 *   public TreeNodeP right;
 *   public TreeNodeP parent;
 *   public TreeNodeP(int key, TreeNodeP parent) {
 *     this.key = key;
 *     this.parent = parent;
 *   }
 * }
 */
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
