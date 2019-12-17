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
    public int depthSum(List<NestedInteger> nestedList) {
        return getSum(nestedList, 1);
    }
    
    private int getSum(List<NestedInteger> nestedList, int depth) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger item = nestedList.get(i);
            if (depth > 1 && i == 0) {
                System.out.print("(");
            }
            if (i > 0) {
                System.out.print(" + ");
            }
            
            if (item.isInteger()) {
                sum += item.getInteger() * depth;
                System.out.print(item.getInteger());
            } else {
                sum += getSum(item.getList(), depth + 1);
            }
            
            if (depth > 1 && i == nestedList.size() - 1) {
                System.out.print(") * " + depth);
            }
        }
        return sum;
    }
}
//time: O(n), space: O(n)