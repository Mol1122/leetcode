/*
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.


nums = [0,1,1,1,1,1,0,0,0]
6 -> [1,1,1,0,0,0]


zeroCount = 4
oneCount = 5
    0,1,1,1,1,1,0,0,0
    0 1 2 3 4 5 5 5 5
max = 6

subarray with equal number of -1 and 1
  -1 1 1 -1 <=> subarray sum is 0
         2              8
    -1 1 1 1 1 1 -1 -1 -1
    i
         j
    -1 0 1.2.3 4  3. 2. 1

*/

public class Main {
    public static int getMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> sum2index = new HashMap<>();
        sum2index.put(0, -1);
        int max = Integer.MIN_VALUE, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : nums[i];
            if (sum2index.containsKey(sum)) {
                max = Math.max(max, i - sum2index.get(sum));
            }
            if (!sum2index.containsKey(sum)) {
                sum2index.put(sum, i);
            }
        }
        return max == Integer.MIN_VALUE ? 0: max;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,1,1,1,0,0,0};
        int length = getMaxLength(nums); //6

        // for (int num : nums) {
        //     System.out.print(num + ", ");
        // }
        System.out.println(length);


        int[] nums1 = new int[]{0,0,0,0};
        int length1 = getMaxLength(nums1); //6
        System.out.println(length1);
    }
}
//time: O(n), space: O(n)
