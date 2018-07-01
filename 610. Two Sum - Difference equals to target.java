public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i] + target; //被除数
            if (map.containsKey(sum)) {
                int[] result = new int[2];
                result[0] = map.get(sum) + 1;
                result[1] = i + 1;
                return result;
            }
            int diff = nums[i] - target; //减数
            if (map.containsKey(diff)) {
                int[] result = new int[2];
                result[0] = map.get(diff) + 1;
                result[1] = i + 1;
                return result;
            }
            map.put(nums[i], i);
        }
        return null;
    }
}

/* 算法：利用HashMap,分情况被减数和减数去判断
** 时间复杂度：O(n)
** 空间复杂度：O(n) */