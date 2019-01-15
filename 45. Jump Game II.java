class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = 0, step = 0;
        
        while (end < nums.length - 1) {
            step++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (nums[i] + i > farthest) {
                    farthest = nums[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return step;

        //会超时
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         int n = nums.length;
//         int[] f = new int[n];
//         if (nums[0] >= nums.length && nums.length > 1) {
//             return 1;
//         }
//         if (nums.length == 1) {
//             return 0;
//         }
        
//         Arrays.fill(f, Integer.MAX_VALUE);
//         f[0] = 0;
           
//         for (int i = 0; i < n; i++) {
//             for (int j = 1; j <= nums[i]; j++) {
//                 if (i + j < n && f[i] != Integer.MAX_VALUE) {
//                     f[i + j]  = Math.min(f[i + j], f[i] + 1);
//                 } else {
//                     break;
//                 }
//             }
//         }
  
//         if (f[n - 1] == Integer.MAX_VALUE) {
//             return -1;
//         }
//         return f[n - 1];
    }
}