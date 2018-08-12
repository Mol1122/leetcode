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
        BITree bitree = new BITree(10000);
        
        for (int i = 0; i < A.length; i++) {
            results.add(bitree.getPrefixSum(A[i] - 1));
            bitree.increase(A[i], 1);
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
    
    private int lowbit(int x) {
        return x & (-x);
    }
}

/* 算法：基于“值的范围”构建 Binary Indexed Tree
** 时间复杂度：O(logn) update, findSum */