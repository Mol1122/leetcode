class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return false;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                for (int idx : map.get(nums[i])) {
                    if (Math.abs(i - idx) <= k) {
                        return true;
                    }
                }
                map.get(nums[i]).add(i);
            } else {
                map.put(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
        }
        return false;
    }
}

/* 时间复杂度：O(n^2) */