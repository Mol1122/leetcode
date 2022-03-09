/**
 Given the root of a binary tree, return the maximum average value of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.

A subtree of a tree is any node of that tree plus all its descendants.

The average value of a tree is the sum of its values, divided by the number of nodes.

 

Example 1:


Input: root = [5,6,1]
Output: 6.00000
Explanation: 
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
Example 2:

Input: root = [0,null,1]
Output: 1.00000
 */
 
class Solution {
    public double maximumAverageSubtree(TreeNode root) {
        if (root == null) {
            return 0.0;
        }
        Pair[] max = {new Pair(Integer.MIN_VALUE, Integer.MIN_VALUE)};
        helper(root, max);
        
        return (double)max[0].sum / max[0].size;
    }
    
    private Pair helper(TreeNode root, Pair[] max) {
        if (root == null) {
            return new Pair(0, 0);
        }
        Pair left = helper(root.left, max);
        Pair right = helper(root.right, max);
        
        int sum = left.sum + right.sum + root.val;
        int size = left.size + right.size + 1;
        Pair p = new Pair(sum, size);

        if (sum * max[0].size >= max[0].sum * size) {
            max[0] = p;
        }
        return p;
    }
}

class Pair {
    int sum, size;
    
    public Pair(int sum, int size) {
        this.sum = sum;
        this.size = size;
    }
}