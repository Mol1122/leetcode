/* You are given a 0-indexed array nums that consists of n distinct positive integers. Apply m operations to this array, where in the ith operation you replace the number operations[i][0] with operations[i][1].

It is guaranteed that in the ith operation:

operations[i][0] exists in nums.
operations[i][1] does not exist in nums.
Return the array obtained after applying all the operations.

 

Example 1:

Input: nums = [1,2,4,6], operations = [[1,3],[4,7],[6,1]]
Output: [3,2,7,1]
Explanation: We perform the following operations on nums:
- Replace the number 1 with 3. nums becomes [3,2,4,6].
- Replace the number 4 with 7. nums becomes [3,2,7,6].
- Replace the number 6 with 1. nums becomes [3,2,7,1].
We return the final array [3,2,7,1].
Example 2:

Input: nums = [1,2], operations = [[1,3],[2,1],[3,2]]
Output: [2,1]
Explanation: We perform the following operations to nums:
- Replace the number 1 with 3. nums becomes [3,2].
- Replace the number 2 with 1. nums becomes [3,1].
- Replace the number 3 with 2. nums becomes [2,1].
We return the array [2,1]. */
class Solution {
    public int[] arrayChange(int[] nums, int[][] ops) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        Map<Integer, Integer> val2index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            val2index.put(nums[i], i);
        }
        for (int[] op : ops) {
            nums[val2index.get(op[0])] = op[1];
            val2index.put(op[1], val2index.get(op[0]));
            val2index.remove(op[0]);
        }
        return nums;
    }
}
// 因为会超时，我们就要想能不能linear. 因为有顺序要求，所以只能遍历ops. 因为要更新值，所以要记录index of the original value，并且更新后更改记录
// 一开始的想法是先遍历operations再遍历nums去处理update，但是超时，那我们可以反过来先遍历nums. 因为operation是根据value去update,所以map是val -> index