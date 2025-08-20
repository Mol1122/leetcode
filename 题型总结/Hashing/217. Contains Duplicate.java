class Solution {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                return true;
            } else {
                map.put(n, 1);
            }
        }
        return false;
    }
}

/* 算法：hash
** 时间复杂度：O(n) */