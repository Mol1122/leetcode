public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int i = 0, j = 31;
        while (i < j) {
            n = reverse(n, i, j);
            i++;
            j--;
        }
        return n;
    }
    
    private int reverse(int n, int i, int j) {
        int left_bit = ((n >> j) & 1);
        int right_bit = ((n >> i) & 1);
        if (left_bit != right_bit) {
            n ^= ((1 << i) | (1 << j));
        }
        return n;
    }
}
//time: O(#bits ^ 2)
