class Solution {
    public int longestMountain(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 0;
        int[] left = new int[nums.length], right = new int[nums.length];
        
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                right[i] = right[i + 1] + 1;
            } 
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                left[i] = left[i - 1] + 1;
            } 
        }
        for (int i = 0; i < nums.length; i++) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans == 1 ? 0 : ans;
        
//         //One pass and O(1) space
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         int ans = 0, left = 0, right = 0;
//         for (int i = 1; i < nums.length; i++) {
//             if (right > 0 && nums[i] > nums[i - 1] || nums[i] == nums[i - 1]) {
//                 left = 0;
//                 right = 0;
//             }
//             if (nums[i] < nums[i - 1]) {
//                 right++;
//             }
//             if (nums[i] > nums[i - 1]) {
//                 left++;
//             }
//             if (left > 0 && right > 0) {
//                 ans = Math.max(ans, left + right + 1);
//             }
//         }
//         return ans;
    }
}