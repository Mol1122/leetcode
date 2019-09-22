/* Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex 
which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, 
the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any. */

class Solution {
    int[] sum;
    Random rand;
    int max;
    public Solution(int[] w) {
        rand = new Random();
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
        max = sum[w.length - 1];
    }
    
    public int pickIndex() {
        int target = rand.nextInt(max) + 1;    // plus one because index does not have 0 weight
        int start = 0; 
        int end = sum.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (sum[mid] == target) {
                return mid;
            } else if (sum[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */