/* Given an integer array, find a continuous subarray where the sum of numbers is the biggest. 
Your code should return the index of the first number and the index of the last number. 
(If their are duplicate answer, return the minimum one in lexicographical order)

Example
Example 1:

Input: [-3, 1, 3, -3, 4]
Output: [1, 4]
Example 2:

Input: [0, 1, 0, 1]
Output: [0, 3]
Explanation: The minimum one in lexicographical order. */

//Method 1
public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        results.add(0);
        results.add(0);
        int sum = 0, start = 0, end = 0;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
                start = end = i;
            } else {
                sum += nums[i];
                end = i;
            }
            if (sum > max) {
                max = sum;
                results.set(0, start);
                results.set(1, end);
            }
        }
        return results;
    }
}
//time: O(n), space: O(1)

//Method 2
public class Solution {
    /**
     * @param a: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int sum = 0, min = 0, max = Integer.MIN_VALUE;
        Map<Integer, Integer> sum2index = new HashMap<>();
        sum2index.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (max < sum - min) {
                max = sum - min;
                results = Arrays.asList(sum2index.get(min) + 1, i);
            }
            sum2index.putIfAbsent(sum, i);
            min = Math.min(min, sum);
        }
        return results;
    }
}
//time: O(n), spcae: O(n)
//         i
//---------min
//         *****
//--------------curr sum
//             j