/* Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.

Assumptions

The given array is not null
Examples

A = {1, 2, 1, 2, 1}, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, return [1]
A = {1, 2, 2, 3, 1, 3}, return []
 */

public class Solution {
  public List<Integer> majority(int[] nums) {
    Set<Integer> results = new HashSet<>();
    if (nums == null || nums.length < 3) {
        for (int num : nums) {
            results.add(num);
        }
        return new ArrayList<>(results);
    }
    int candidate1 = nums[0];
    int count1 = 1;
    int candidate2 = nums[1];
    int count2 = 1;

    for (int i = 2; i < nums.length; i++) {
        if (nums[i] == candidate1) {
            count1++;
        } else if (nums[i] == candidate2) {
            count2++;
        } else if (count1 == 0) {
            candidate1 = nums[i];
            count1 = 1;
        } else if (count2 == 0) {
            candidate2 = nums[i];
            count2 = 1;
        } else {
            count1--;
            count2--;
        }
    }
    count1 = count2 = 0;
    for (int num : nums) {
        if (num == candidate1) {
            count1++;
        } else if (num == candidate2) {
            count2++;
        }
    }
    if (count1 > nums.length / 3) {
        results.add(candidate1);
    }
    if (count2 > nums.length / 3) {
        results.add(candidate2);
    }
    return new ArrayList<Integer>(results);
  }
}
//time: O(n), space: O(1)