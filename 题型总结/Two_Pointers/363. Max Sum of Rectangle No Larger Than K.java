/* Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.

It is guaranteed that there will be a rectangle with a sum no larger than k.


Example 1:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).

Example 2:

Input: matrix = [[2,2,-1]], k = 3
Output: 3 */

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return Integer.MIN_VALUE;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] max = {Integer.MIN_VALUE};
        int[] sums = new int[m];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(sums, 0);
            for (int j = i; j < n; j++) {
                for (int col = 0; col < m; col++) {
                    sums[col] += matrix[j][col];
                }
                getMaxSum(sums, max, k);
                if (max[0] == k) {
                    return max[0];
                }
            }
        }
        return max[0];
    }
    
    private void getMaxSum(int[] nums, int[] max, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        int sum = 0;
        
        set.add(0); //important! in order to get an empty subarray
        for (int num : nums) {
            sum += num;
            Integer x = set.ceiling(sum - k);
            if (x != null) {
                max[0] = Math.max(max[0], sum - x);
            }
            set.add(sum);
        }
    }
}
//算法：很容易想到2D -> 1D, 和 prefixSum. sum - x <= k, sum - k <= x. 所以就是要找出the ceiling of (sum - k) = x, 然后比较得出max sum
//time: O(n^2mlogm), space: O(m)