class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0, prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //very important, 表示prefixSum = 1在index-1， 说明要回到array最开始的时候，那么是减0加一，因为要囊落prefixSum = 0                         //的前一个数  
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum - k)) {
                result = Math.max(result, i - map.get(prefixSum - k));
            }
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }
        return result;
    }
}

/*  Time Complexity: O(n)
Space Complexity: O(n)

Subarray sum from index i to index j (e.g array[i:j]) = sum(array[0:j]) - sum(array[0:i-1])
So this problem is another two-sum like problem, we can calculate prefix-sum of the given array and use a hashmap to look up answer.  */