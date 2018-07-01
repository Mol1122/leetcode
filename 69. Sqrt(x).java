class Solution {
    public int mySqrt(int x) {
        long start = 1, end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end * end <= x) {
            return (int)end;
        }
        return (int)start;
    }
}

/* 算法：按照值域的二分 */