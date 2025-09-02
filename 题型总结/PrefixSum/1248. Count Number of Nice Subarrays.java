/* Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

 

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16                                     */

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> sum2count = new HashMap<>();
        sum2count.put(0, 1);
        int sum = 0, count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                sum++;
            }
            if (sum2count.containsKey(sum - k)) {
                count += sum2count.get(sum - k);
            }
            sum2count.put(sum, sum2count.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
//time: O(n), space: O(n)

// 1, 1, 1, 0, 0, 0, 0


//---------------
//               i*****
//---------------------j, sum