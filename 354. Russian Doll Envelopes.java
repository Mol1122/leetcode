class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0]) {
                    return arr2[1] - arr1[1]; //decreasing in height
                } else {
                    return arr1[0] - arr2[0]; //increasing in weight
                }
            }
        });
        //use the longest increasing subsequence method to find the longest increasing height
        int length = 0; 
        int[] dp = new int[envelopes.length + 1];
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, length, envelope[1]);
            if (index < 0) {
                index = -index - 1; //如果找不到返回-1
            }
            dp[index] = envelope[1];
            if (length == index) {
                length++;
            }
        }
        return length;
    }
}

/* 算法：动态规划。 1. Sort the array. Ascend on width and descend on height if width are same.
                 2. Find the longest increasing subsequence based on height. 
    Since the width is increasing, we only need to consider height.
    
    时间复杂度：O(nlogn) 
    空间复杂度: O(n) */