public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n = n >>> 1;
        }
        return count;
    }
}

/* line 6, 1 = 000000000000001. (n & 1) is 1 means the last digit is 1, shift all bits in n to the right by 1 to check the next 
** digit */