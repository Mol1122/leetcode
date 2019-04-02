/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
      if (one == null && two == null) {
          return true;
      } else if (one == null || two == null) {
          return false;
      } else if (one.key != two.key) {
          return false;
      }
    
      return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right) ||
             isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
  }
}

/* 时间复杂度：O(4^(log_2n)) = O(2^2 * (log_2n)) = O(2^(lon_n ^2)) = O(n^2) 最balanced的情况就是这样，也是最坏的情况
** 空间复杂度：O(height)



              (5a, 5b)
              /  |  |    \
            (3a, 3b)  
          /  |  |  \


*/
