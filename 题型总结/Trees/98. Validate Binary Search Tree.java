/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Method 1
// class Solution {
//     TreeNode lastNode = null;
//     boolean isValid = true;
//     public boolean isValidBST(TreeNode root) {
//         //inorder traverse
//         inorderTraverse(root);
//         return isValid;
//     }
    
//     private void inorderTraverse(TreeNode root) {
//         if (root == null) {
//             return;
//         }
//         inorderTraverse(root.left);
//         if (lastNode != null && lastNode.val >= root.val) {
//             isValid = false;
//             return;
//         }
//         lastNode = root; // 难点，易忘
//         inorderTraverse(root.right);
//     }
// }

//Method 2
class Solution {
    
    public boolean isValidBST(TreeNode root) {
        //分治法
        return dividedConqure(root).isValid;
    }
    
    private ResultType dividedConqure(TreeNode root) {
        if (root == null) {
            return new ResultType(true);
        }
        ResultType left = dividedConqure(root.left);
        ResultType right = dividedConqure(root.right);
        
        if (!left.isValid || !right.isValid) {
            return new ResultType(false);
        }
        if (left.maxNode != null && left.maxNode.val >= root.val) {
            return new ResultType(false);
        }
        if (right.minNode != null && right.minNode.val <= root.val) {
            return new ResultType(false);
        }
        //is BST
        ResultType result = new ResultType(true);
        result.minNode = left.minNode == null ? root : left.minNode;
        result.maxNode = right.maxNode == null ? root : right.maxNode;
        return result;
    }
}

class ResultType {
    boolean isValid;
    TreeNode maxNode, minNode;
    
    public ResultType(boolean isValid) {
        this.isValid = isValid;
        maxNode = null;
        minNode = null;
    }
}

//Method 3 (Recommanded)
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        } else if (root.val <= min || root.val >= max) {
            return false;
        }
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }
}
//time: O(n), space: O(height)

/* 算法：inorder的写法，时间复杂度：O(n)， 因为要遍历所有的node 
**      分治算法和inorder都要求掌握
** 难点： 忘记修改root的值，利用全局变量去记录，避免不必要的recursion */