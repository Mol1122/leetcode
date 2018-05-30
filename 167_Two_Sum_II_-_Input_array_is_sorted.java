class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int start = 0, end = numbers.length - 1;
        
        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                int[] result = new int[2];
                result[0] = start + 1;
                result[1] = end + 1;
                return result;
            } else if (numbers[start] + numbers[end] > target) {
                end--;
            } else {
                start++;
            }
        }
        return null;
    }
}

/* 算法：双指针 
 * 难点：相等后直接return, 最外层的return null*/