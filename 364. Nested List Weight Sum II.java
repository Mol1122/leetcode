/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    int flatSum = 0, maxDepth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int depthSum = dfs(nestedList, 1);
        return flatSum * (maxDepth + 1) - depthSum;
    }
    
    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
            
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                maxDepth = Math.max(maxDepth, depth);
                sum += item.getInteger() * depth;
                flatSum += item.getInteger();
            } else {
                sum += dfs(item.getList(), depth + 1);
            }
        }
        return sum;
    }
}

/* 难点在于flatSum * (maxDepth + 1) - depthSum 公式。flatSum就是所有数字相加，depthSum就是前一个题目的sum */