public class Solution {
    /**
     * @param A: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0 || start > end) {
            return 0;
        }
        int n = A.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }
        //非常直白的方式，时间复杂度：O(n^2)
        // int count = 0;
        // for (int i = 0; i <= n; i++) {
        //     for (int j = i + 1; j <= n; j++) {
        //         int diff = prefixSum[j] - prefixSum[i];
        //         if (diff >= start && diff <= end) {
        //             count++;
        //         }
        //     }
        // }
        // return count;
        
        //利用二分法找在区间内的个数. 时间复杂度:O(nlogn)
        //难点：prefixSum[i] - prefixSum[x] > start -> prefixSum[i] - start > prefixSum[x]
        //      prefixSum[i] - prefixSum[x] < end -> prefixSum[i] - end < prefixSum[x]
        int count = 0;
        for (int i = 0; i <= n; i++) {
            int left = prefixSum[i] - end;
            int right = prefixSum[i] - start;
            count += findRange(prefixSum, right) - findRange(prefixSum, left - 1);
        }
        return count;
    }
    
    private int findRange(int[] arr, int number) { //找第一个大于等于number的数, 这样找到的那个index就是<=number的个数
        int start = 0, end = arr.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > number) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (arr[start] <= number) {
            return start;
        }
        if (arr[end] <= number) {
            return start;
        }
        return -1;
    }
}