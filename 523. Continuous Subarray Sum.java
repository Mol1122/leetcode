class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); //remainder, index
        map.put(0, -1); //!!!remainder is 0 means need to search all the way to the left, which index is -1
        int runningSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) {
                runningSum %= k;
            }
            if (map.get(runningSum) != null) {
                if (i - map.get(runningSum) > 1) {
                    return true;
                } 
            } else {
                map.put(runningSum, i); //存在过了就不需要更新了
            }
            
        }
        return false;
    }
}

/* 算法：用map来保存余数对应的index在哪，如果已经出现过，并且长度大于2，那么肯定是true
** 难点：map里面存的不是sum，而是余数，因为只有余数能够判断是否为k的倍数 
** 时间复杂度：O(n) 
** 空间复杂度：O(n) */