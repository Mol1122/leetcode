class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        return count;
    }
}

/* 算法：从2开始循环，2相乘另外一个数只要小于n的都不是答案。O(n^2) 对于每一个i，你都走了一遍j，然后j的可能性是O(n)
*  面试：只关心worst case的时间复杂度，这就是big-O的复杂度 */