/* Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example
Example 1

Input:
nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
Output:
["2", "4->49", "51->74", "76->99"]
Explanation:
in range[0,99],the missing range includes:range[2,2],range[4,49],range[51,74] and range[76,99]
Example 2

Input:
nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
Output:
["4->6"]
Explanation:
in range[0,7],the missing range include range[4,6] */

public class Solution {
    /*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            getRange(lower, upper, results);
            return results;
        }
        getRange((long)lower, (long)nums[0] - 1, results);
        for (int i = 1; i < nums.length; i++) {
            getRange((long)nums[i - 1] + 1, (long)nums[i] - 1, results);
        }
        getRange((long)nums[nums.length - 1] + 1, upper, results);
        return results;
    }
    
    private void getRange(long lower, long upper, List<String> results) {
        if (lower > upper) {
            return;
        }
        if (lower == upper) {
            results.add(lower + "");
            return;
        }
        results.add(lower + "->" + upper);
    }
}
//time: O(n), space: O(n)