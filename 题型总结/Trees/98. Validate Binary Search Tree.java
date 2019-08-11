/**
 Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees. */

public class Solution {
  public boolean isBST(TreeNode root) {
      //preorder
      //return isBST(root, null, null);
    
      //postorder
      return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  //postorder
  private boolean helper(TreeNode root, int min, int max) {
      if (root == null) {
          return true;
      }
      if (!helper(root.left, min, root.key) || !helper(root.right, root.key, max)) {
          return false;
      }
      if (root.key >= max || root.key <= min) {
          return false;
      }
      return true;
  }
  
  //preorder
  private boolean isBST(TreeNode root, TreeNode minNode, TreeNode maxNode) {
      if (root == null) {
          return true;
      }
      if (minNode != null && root.key <= minNode.key) {
          return false;
      }
      if (maxNode != null && root.key >= maxNode.key) {
          return false;
      }
      return isBST(root.left, minNode, root) && isBST(root.right, root, maxNode);
  }
}

//preorder
public class Solution {
  public boolean isBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isBST(TreeNode root, int min, int max) {
    if (root == null) {
      return true;
    } else if (root.key <= min || root.key >= max) {
      return false;
    }
    return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);
  }
}
//time: O(n), space: O(height)

//time: O(n), space: O(height)

// iterative way, 也是inorder的写法
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null;
        
        while (root != null) {
            stack.offerLast(root);
            root = root.left;
        }
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peekLast();
            if (prev != null && prev.val >= curr.val) {
                return false;
            }
            prev = curr;
            if (curr.right == null) {
                curr = stack.pollLast();
                while (!stack.isEmpty() && stack.peekLast().right == curr) {
                    curr = stack.pollLast();
                }
            } else {
                curr = curr.right;
                while (curr != null) {
                    stack.offerLast(curr);
                    curr = curr.left;
                }
            }
        }
        return true;
    }
    
    private void pushLeft(TreeNode curr, Deque<TreeNode> stack) {
        while (curr != null) {
            stack.offerLast(curr);
            curr = curr.left;
        }
    }
}

//inorder traversal的另一种写法
//time: O(n), space: O(height)
