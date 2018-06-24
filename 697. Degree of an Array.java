class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> index = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        int minLength = Integer.MAX_VALUE, frequency = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (!index.containsKey(nums[i])) {
                index.put(nums[i], i);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            if (count.get(nums[i]) == frequency) {
                minLength = Math.min(minLength, i - index.get(nums[i]) + 1);
            } else if (count.get(nums[i]) > frequency) {
                frequency = count.get(nums[i]);
                minLength = i - index.get(nums[i]) + 1;
            }
        }
        return minLength;
    }
}

/* 算法：边遍历边保存出现最多次数和index
** 时间复杂度：O(n) */