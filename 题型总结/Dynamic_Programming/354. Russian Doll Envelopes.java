/* Give a number of envelopes with widths and heights given as a pair of integers (w, h). 
One envelope can fit into another if and only if both the width and height of one envelope is 
greater than the width and height of the other envelope.
Find the maximum number of nested layers of envelopes.

Example
Example 1:

Input：[[5,4],[6,4],[6,7],[2,3]]
Output：3
Explanation：
the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
Example 2:

Input：[[4,5],[4,6],[6,7],[2,3],[1,1]]
Output：4
Explanation：
the maximum number of envelopes you can Russian doll is 4 ([1,1] => [2,3] => [4,5] / [4,6] => [6,7]). */

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1]; //decreasing
                }
                return a[0] - b[0]; //increasing
            }
        });
        int n = envelopes.length;
        int[] f = new int[n];
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            max = Math.max(max, f[i]);
        }
        return max;
    }
}
//算法：让一个dim increasing, 然后对另一个dimention做longest increasing subsequence
//注意：如果第一个dim相等，但么第二个是decreasing, 这样保证在找longest increasing 
//subsequence的时候是strictly increasing
//time: O(nlogn + n^2), space: O(n)

public class Solution {
    /*
     * @param envelopes: a number of envelopes with widths and heights
     * @return: the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || 
            envelopes[0] == null || envelopes[0].length == 0) {
                return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            }
        });
        int[] dp = new int[envelopes.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        
        int length = 0;
        for (int[] envelope : envelopes) {
            int index = binarySearch(dp, envelope[1]);
            dp[index] = envelope[1];
        }
        for (int i = envelopes.length; i >= 0; i--) {
            if (dp[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        return 0;
    }
    
    private int binarySearch(int[] dp, int num) {
        int start = 0, end = dp.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (dp[mid] < num) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }
}

/* 时间复杂度：O(nlogn) 
    空间复杂度: O(n) */