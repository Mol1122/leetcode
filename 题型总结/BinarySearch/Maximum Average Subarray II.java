/* Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal 
to given length k. 

Example 1:

Input:
[1,12,-5,-6,50,3]
3
Output:
15.667
Explanation:
 (-6 + 50 + 3) / 3 = 15.667
Example 2:

Input:
[5]
1
Output:
5.000
*/

public class Solution {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0.0;
        }
        double start = -1e10, end = 1e10, esp = 1e-6;
        
        while (start + esp < end) {
            double mid = start + (end - start) / 2;
            if (check(nums, mid, k)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (check(nums, end, k)) {
            return end;
        }
        return start;
    }
    
    private boolean check(int[] nums, double avg, int k) {
        double[] sums = new double[nums.length + 1];
        double max = 0, min = 0;
        
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + (nums[i - 1] - avg);
            if (i >= k && sums[i] - min >= 0) {
                return true;
            }
            if (i >= k) {
                min = Math.min(min, sums[i - k + 1]); //i-k+1 是为了下一个iteration
            }
        }
        return false;
    }
}
//算法：二分答案。思路应该是看到max subarray，想到用prefixSum;然后要求的是avg, 就用二分去猜avg是多少
//time: O(nlogn), space: O(n)