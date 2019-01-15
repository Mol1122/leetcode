/* 595. Binary Tree Longest Consecutive Sequence
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example
For example,

   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2. */

public class Solution {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int length = 1;
        
        if (root.left != null && root.left.val == root.val + 1) {
            length = Math.max(length, left + 1);
        }
        if (root.right != null && root.right.val == root.val + 1) {
            length = Math.max(length, right + 1);
        }
        max = Math.max(max, length);
        return length;
    }
}

/* 614. Binary Tree Longest Consecutive Sequence II  https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence-ii/description?_from=ladder&&fromId=1
Given a binary tree, find the length of the longest consecutive sequence path.
The path could be start and end at any node in the tree

Example
    1
   / \
  2   0
 /
3
Return 4 // 0-1-2-3 */


//Binary Tree Longest Consecutive Sequence II
public class Solution {
    int max = 0;
    public int longestConsecutive2(TreeNode root) {
        ResultType rt = helper(root);
        return max;
    }   
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int increasing = 1, decreasing = 1; //包含自身
        if (root.left != null) {
            if (root.val == root.left.val + 1) {
                increasing = Math.max(increasing, left.increasing + 1);
            }
            if (root.val == root.left.val - 1) {
                decreasing = Math.max(decreasing, left.decreasing + 1);
            }
        }
        if (root.right != null) {
            if (root.val == root.right.val + 1) {
                increasing = Math.max(increasing, right.increasing + 1);
            }
            if (root.val == root.right.val - 1) {
                decreasing = Math.max(decreasing, right.decreasing + 1);
            }
        }
        max = Math.max(max, increasing + decreasing - 1);
        return new ResultType(increasing, decreasing);
    }
}
class ResultType {
    int increasing, decreasing; 
    public ResultType(int increasing, int decreasing) {
        this.increasing = increasing;
        this.decreasing = decreasing;
    }
}

/* 
619. Binary Tree Longest Consecutive Sequence III
It's follow up problem for Binary Tree Longest Consecutive Sequence II

Given a k-ary tree, find the length of the longest consecutive sequence path.
The path could be start and end at any node in the tree

Example
An example of test data: k-ary tree 5<6<7<>,5<>,8<>>,4<3<>,5<>,3<>>>, denote the following structure:


     5
   /   \
  6     4
 /|\   /|\
7 5 8 3 5 3

Return 5, // 3-4-5-6-7 */
//Binary Tree Longest Consecutive Sequence III
public class Solution {
    int max = 0;
    public int longestConsecutive3(MultiTreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }
    
    private ResultType helper(MultiTreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType[] results = new ResultType[root.children.size()];
        for (int i = 0; i < root.children.size(); i++) {
            results[i] = helper(root.children.get(i));
        }
        
        int increasing = 1, decreasing = 1;
        for (int i = 0; i < root.children.size(); i++) {
            if (root.children.get(i).val == root.val + 1) {
                increasing = Math.max(increasing, results[i].increasing + 1);
            }
            if (root.children.get(i).val == root.val - 1) {
                decreasing = Math.max(decreasing, results[i].decreasing + 1);
            }
        }
        max = Math.max(max, increasing + decreasing - 1);
        return new ResultType(increasing, decreasing);
    }
}

class ResultType {
    int increasing, decreasing;
    
    public ResultType(int increasing, int decreasing) {
        this.increasing = increasing;
        this.decreasing = decreasing;
    }
}
