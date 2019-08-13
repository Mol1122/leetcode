/**
 Given K nodes in a binary tree, find their lowest common ancestor.

Assumptions

K >= 2

There is no parent pointer for the nodes in the binary tree

The given K nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2, 3, 14 is 5

The lowest common ancestor of 2, 3, 9 is 9 */
 
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
      Set<TreeNode> set = new HashSet<>();
      for (TreeNode node : nodes) {
          set.add(node);
      }
      return lca(root, set);
  }
  
  private TreeNode lca(TreeNode root, Set<TreeNode> set) {
      if (root == null || set.contains(root)) {
          return root;
      }
      TreeNode left = lca(root.left, set);
      TreeNode right = lca(root.right, set);
    
      if (left != null && right != null) {
          return root;
      }
      return left == null ? right : left;
  }
}
//time: O(n), space: O(height)