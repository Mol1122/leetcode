/* The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:

     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9. */

public class Solution {
  public int rob(TreeNode root) {
    ResultType rt = visit(root);
    return Math.max(rt.rob, rt.not_rob);
  }

  private ResultType visit(TreeNode root) {
    if (root == null) {
        return new ResultType();
    }
    ResultType left = visit(root.left);
    ResultType right = visit(root.right);
    
    ResultType rt = new ResultType();
    rt.rob = left.not_rob + right.not_rob + root.key;
    rt.not_rob = Math.max(left.rob, left.not_rob) + Math.max(right.rob, right.not_rob);
    return rt;
  }
}

class ResultType {
    int rob, not_rob;

    public ResultType() {
        rob = not_rob = 0;
    }
}
//time: O(n), space: O(n)