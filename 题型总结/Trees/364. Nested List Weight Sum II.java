/**
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
 
class Solution {
    int flatSum = 0, maxDepth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int sum = getSum(nestedList, 1);
        return flatSum * (maxDepth + 1) - sum;
    }
    
    private int getSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger num : nestedList) {
            if (num.isInteger()) {
                sum += num.getInteger() * depth;
                maxDepth = Math.max(maxDepth, depth);
                flatSum += num.getInteger();
            } else {
                sum += getSum(num.getList(), depth + 1);
            }
        }
        return sum;
    }
}
//time: O(n), space: O(n)
//难点：flatSum * (maxDepth + 1) - sum
//flatSum * maxDepth，就是最大的sum是多少，减掉正着数的sum,就是反着的sum了