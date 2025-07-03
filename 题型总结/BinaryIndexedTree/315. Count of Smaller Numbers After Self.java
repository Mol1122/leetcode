/* Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].

 

Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Example 2:

Input: nums = [-1]
Output: [0]
Example 3:

Input: nums = [-1,-1]
Output: [0,0]
 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104 */

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int x = 10000;
        BITree tree = new BITree(20001);
        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = tree.getPrefixSum(nums[i] + x - 1);
            results.add(sum);
            tree.increase(nums[i] + x, 1);
        }
        Collections.reverse(results);
        return results;
    }
}

class BITree {
    int[] bit;

    public BITree(int range) {
        bit = new int[range + 1];
    }

    public void increase(int index, int delta) {
        for (int i = index + 1; i < bit.length; i = i + lowbit(i)) {
            bit[i] += delta;
        }
    }

    public int lowbit(int x) {
        return x & (-x);
    }

    public int getPrefixSum(int index) {
        int sum = 0;

        for (int i = index + 1; i > 0; i = i - lowbit(i)) {
            sum += bit[i];
        }
        return sum;
    }
}
//time: O(nlog 20000), space: O(20000)