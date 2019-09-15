/**
It's follow up problem for Binary Tree Longest Consecutive Sequence II

Given a k-ary tree, find the length of the longest consecutive sequence path.
The path could be start and end at any node in the tree

Example
Example 1:

Input:
5<6<7<>,5<>,8<>>,4<3<>,5<>,31<>>>
Output:
5
Explanation:
     5
   /   \
  6     4
 /|\   /|\
7 5 8 3 5 31

return 5, // 3-4-5-6-7
Example 2:

Input:
1
Output:
1
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