/* Given an integer array, find a subarray with sum closest to zero.
Return the indexes of the first number and last number.

You only need to consider one of the possibilities. See the example for details.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


It is guaranteed that the sum of any numbers is in 
[
−
2
31
,
2
31
−
1
]
[−2 
31
 ,2 
31
 −1].

Example
Example1

Input: 
[-3,1,1,-3,5] 
Output: 
[0,2]
Explanation: [0,2], [1,3], [1,1], [2,2], [0,4]
Example2

Input: 
[2147483647]
Output: 
[0,0] */

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        if (nums.length == 1) {
            return new int[]{0, 0};
        }
        Pair[] sums = new Pair[nums.length + 1];
        sums[0] = new Pair(0, 0);

        for (int i = 1; i <= nums.length; i++) {
            sums[i] = new Pair(sums[i - 1].sum + nums[i - 1], i);
        }
        Arrays.sort(sums, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });
        int diff = Integer.MAX_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            if (diff > sums[i].sum - sums[i - 1].sum) {
                diff = sums[i].sum - sums[i - 1].sum;
                int[] temp = new int[]{sums[i - 1].index - 1, sums[i].index - 1};
                Arrays.sort(temp);
                result[0] = temp[0] + 1;
                result[1] = temp[1];
            }
        }
        return result;
    }
}

class Pair {
    int sum, index;

    public Pair(int sum, int index) {
        this.sum = sum;
        this.index = index;
    }
}