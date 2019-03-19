class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int start = 1, end = A.length - 2;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (A[mid] < A[mid - 1]) {
                end = mid;
            } else if (A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                start = mid;
            }
        }
        if (A[start] < A[end]) {
            return end;
        } else {
            return start;
        }
    }
}

/* 算法：binary search
** 时间复杂度：O(logn) */