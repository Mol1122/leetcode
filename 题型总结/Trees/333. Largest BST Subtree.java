/**
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.    
Here's an example:

    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3. 

Follow up:
Can you figure out ways to solve it with O(n) time complexity? */

//Method 1
public class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ResultType rt = dfs(root);
        return rt.ans == 0 ? 1 : rt.ans;
  }

    private ResultType dfs(TreeNode root) {
        if (root == null) {
            return new ResultType();
        }
        ResultType rt = new ResultType();
        ResultType left = dfs(root.left);
        ResultType right = dfs(root.right);
        rt.min = Math.min(left.min, root.val);
        rt.max = Math.max(right.max, root.val);

        if (left.isBST && right.isBST && left.max < root.val && right.min > root.val) {
            rt.ans = left.ans + right.ans + 1;
            rt.isBST = true;
        } else {
            rt.ans = Math.max(left.ans, right.ans);    
            rt.isBST = false;
        }    
        return rt;
    }
}   

class ResultType {
    int ans, min, max;
    boolean isBST;
    
    public ResultType() {
        ans = 0;
        min = Integer.MAX_VALUE;
        max = -Integer.MAX_VALUE;
        isBST = true;
    }
}
//time: O(n), space: O(height)

//Method 2
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = {0};
        helper(root, max);
        return max[0];
    }

    private Pair helper(TreeNode root, int[] max) {
        if (root == null) {
            return new Pair(0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        Pair left = helper(root.left, max);
        Pair right = helper(root.right, max);

        if (!left.isBST || !right.isBST) {
            return new Pair(0, Integer.MAX_VALUE, Integer.MIN_VALUE, false);
        }
        if (root.val <= left.max || root.val >= right.min) {
            return new Pair(0, Integer.MAX_VALUE, Integer.MIN_VALUE, false);
        }

        if (left.count + right.count + 1 > max[0]) {
            max[0] = left.count + right.count + 1;
        }
        return new Pair(left.count + right.count + 1, Math.min(left.min, root.val), Math.max(right.max, root.val), true);
    }
}

class Pair {
    int count;
    int min, max;
    boolean isBST;

    public Pair(int count, int min, int max, boolean isBST) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.isBST = isBST;
    }
}
