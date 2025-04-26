public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int left = findLowerClosest(A, target);
        int right = left + 1;
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            if (isLeftCloser(A, target, left, right)) {
                results[i] = A[left];
                left--;
            } else {
                results[i] = A[right];
                right++;
            }
        }
        
        return results;
    }
    
    private boolean isLeftCloser(int[] arr, int target, int left, int right) {
        if (left < 0) {
            return false;
        }
        if (right >= arr.length) {
            return true;
        }
        if (Math.abs(target - arr[left]) != Math.abs(target - arr[right])) {
            return Math.abs(target - arr[left]) < Math.abs(target - arr[right]);
        }
        return true; //相等的时候仍算左边更近
    }
    
    private int findLowerClosest(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (arr[end] < target) {
            return end;
        } else if (arr[start] < target) {
            return start;
        } else {
            return -1;
        }
    }
}