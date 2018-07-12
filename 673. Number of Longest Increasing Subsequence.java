class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0, max_length = 0, n = nums.length;
        int[] len = new int[n], cnt = new int[n];
        
        for (int i = 0; i < n; i++) {
            len[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[i] == len[j] +1) { //以当前结尾的方案数与 以前一个数结尾的方案数相同，那么加到当前位置即可
                        cnt[i] += cnt[j];
                    } else if (len[i] < len[j] +1) { //increasing sequence
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if (max_length == len[i]) {
                count +=  cnt[i];
            }
            if (max_length < len[i]) {
                count = cnt[i];
                max_length = len[i];
            }
        }
        return count;
    }
}

/* 算法：坐标型动态规划。
        len[i] = 以i为结尾的最长的Longest Increasing Subsequence 长度
        cnt[i] = 以i为结尾的最长的Longest Increasing Subsequence 数量
    时间复杂度：O(n^2)
*/