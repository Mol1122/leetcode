/**
Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. A tweak is defined as a swap of the children of one node in the tree.

Examples

        5

      /    \

    3        8

  /   \

1      4

and

        5

      /    \

    8        3

           /   \

          1     4   */

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

/* 时间复杂度：O(4^(log_2（n）)) = O(2^2 * (log_2（n）)) = O(2^(log_2(n ^2)) = O(n^2) 最balanced的情况就是这样，也是最坏的情况.
             因为一共有log_2(n)层，每层有4个分叉
** 空间复杂度：O(height)



              (5a, 5b)
              /  |  |    \
            (3a, 3b)  
          /  |  |  \


*/
