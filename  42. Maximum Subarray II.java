public class Solution {
    /*
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(List<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int n = nums.size();
        int[] left = new int[n];
        int[] right = new int[n];
        
        int max = Integer.MIN_VALUE, sum = 0, min = 0;
        for (int i = 0; i < n; i++) {
            sum += nums.get(i);
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            left[i] = max;
        }
        max = Integer.MIN_VALUE;
        sum = 0;
        min = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += nums.get(i);
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            right[i] = max;
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, left[i] + right[i + 1]);
        }
        return max;
    }
}

/* 算法：从两边分别找max subarray,因为不能重叠，那么可以遍历分割线。31-34就是在遍历分割线 */