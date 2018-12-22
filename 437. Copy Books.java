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
        for (int page : pages) {
            start = Math.max(start, page);
            end += page;
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (check(pages, mid, k)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (check(pages, start, k)) {
            return start;
        }
        return end;
    }
    
    private boolean check(int[] pages, int limit, int k) {
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