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