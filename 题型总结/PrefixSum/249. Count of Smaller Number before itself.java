/* Give you an integer array (index from 0 to n-1, where n is the size of this array, data value from 0 to 10000) . For each element Ai in the array, count the number of element before this element Ai is smaller than it and return count number array.

Example
Example 1:

Input:
[1,2,7,8,5]
Output:
[0,1,2,3,2]
Example 2:

Input:
[7,8,2,1,3]
Output:
[0,1,0,0,2]
Clarification
Before you do this, we suggest you complete the following three questions: Segment Tree Build， Segment Tree Query II，and Count of Smaller Number before itself I 。 */

public class Solution {
    /**
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        List<Integer> results = new ArrayList<>();
        if (A == null || A.length == 0) {
            return results;
        }
        BITree b = new BITree(10000);
        for (int i = 0; i < A.length; i++) {
            results.add(b.getPrefixSum(A[i] - 1));
            b.increase(A[i], 1);
        }
        return results;
        
       
    }
}

class BITree {
    int[] bit;
    
    public BITree(int range) {
        bit = new int[range + 1];
    }
    
    public void increase(int index, int delta) {
        for (int i = index + 1; i < bit.length; i = i + lowbit(i)) {
            bit[i] += delta;
        }
    }
    
    public int getPrefixSum(int index) {
        int sum = 0;
        for (int i = index + 1; i > 0; i = i - lowbit(i)) {
            sum += bit[i];
        }
        return sum;
    }
    
    public int lowbit(int x) {
        return x & (-x);
    }
}


/* 算法：基于“值的范围”构建 Binary Indexed Tree
** 时间复杂度：O(logn) update, findSum */