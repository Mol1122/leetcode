class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && countBits(n) == 1;
    }
    
    private int countBits(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);
            n = (n >> 1);
        }
        return count;
    }
}
//time: O(#bits), space: O(1)