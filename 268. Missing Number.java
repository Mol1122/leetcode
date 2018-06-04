class Solution {
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] arr = new int[nums.length + 1];
        for (int n : nums) {
            arr[n] = n;
        }
       /* for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        } */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                return i;
            }
        }
        return 0;
    }
}

/* 算法：利用新开数组去一一对应地判断 */