class Solution {
    public boolean isPerfectSquare(int num) {
        long start = (long)1, end = (long)num;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid < num) {
                start =  mid;
            } else {
                end = mid;
            }
        }
        if (start * start == (long)num || end * end == (long)num) {
            return true;
        }
        return false;
    }
}

/* 算法：利用二分法，cast成long是为了防止overflow */