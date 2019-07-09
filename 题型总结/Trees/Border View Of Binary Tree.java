/**
Given a binary tree, return its border view. The border view is defined as follow: first get all the border nodes at left side(from root and always go to the left subtree unless the left subtree does not exist until reach a leaf node), then get all the leaf nodes(from left to right), at last get all the border nodes at right side(similar to left border but from bottom to top), the list of border view should not contain duplicate nodes. Note that for the given root, if it has no left sub-tree or right sub-tree, it is considered the left border/right border, but this rule applies to only the input tree not any sub-tree of it.

Examples:
           1
        /    \
       2      3
      / \    /  \
     4   5   6  7
      \          \
       9           8

          \

            11

the border view =  [1, 2, 4, 9, 11, 5, 8, 7, 3]

1, 2, 4, 9, 11 are the left border, 11, 5, 8, 7 are the leaf nodes, 7, 3, 1 are the right border.
 */
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) {
            return results;
        } 
        if (root != null) {
            results.add(root.val);
            getLeft(root.left, results);
            getLeaves(root.left, results);
            getLeaves(root.right, results);
            getRight(root.right, results);
        }
        return results;
    }
    
    private void getRight(TreeNode root, List<Integer> results) {
        if (root != null) {
            if (root.right != null) {
                getRight(root.right, results);
                results.add(root.val);
            } else if (root.left != null) {
                getRight(root.left, results);
                results.add(root.val);
            }
        }
    }
    
    private void getLeaves(TreeNode root, List<Integer> results) {
        if (root != null) {
            getLeaves(root.left, results);
            if (root.left == null && root.right == null) {
                results.add(root.val);
            }
            getLeaves(root.right, results);
        }
    }
    
    private void getLeft(TreeNode root, List<Integer> results) {
        if (root != null) {
            if (root.left != null) {
                results.add(root.val);
                getLeft(root.left, results);
            } else if (root.right != null) {
                results.add(root.val);
                getLeft(root.right, results);
            }
        }
    }
}

//time: O(n), space: O(height)