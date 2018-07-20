/**
 * Definition for a multi tree node.
 * public class MultiTreeNode {
 *     int val;
 *     List<MultiTreeNode> children;
 *     MultiTreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param root the root of k-ary tree
     * @return the length of the longest consecutive sequence path
     */
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