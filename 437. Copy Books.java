public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int start = 0, end = 0;
        for (int p : pages) { //值域的范围就是[max p, sum all p]
            start = Math.max(start, p);
            end += p;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (check(pages, k, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (check(pages, k, start)) {
            return start;
        }
        return end;
    }
    
    private boolean check(int[] pages, int k, int limit) {
        int count = 0;
        int left = 0;
        for (int p : pages) {
            if (p > limit) {
                return false;
            }
            if (p > left) {
                count++;
                left = limit;
            }
            left -= p;
        }
        return count <= k;
    }
}

/* 算法：按照值域的二分。mid左边是不能满足人数要求的，右边是可以满足人数要求的，找出第一个能满足要求的。
** 时间复杂度：O(logPage * N) */