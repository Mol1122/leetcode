/* You have 4 cards each containing a number from 1 to 9. You need to compute whether they could operated 
through *, /, +, -, (, ) to get the value of 24.

Example
Example 1:

Input：[1, 4, 8, 7]
Output：true
Explanation：8 * （7 - 4） * 1 = 24
Example 2:

Input：[1, 1, 1, 2]
Output：false
Explanation：no way to get 24
Example 3:

Input：[3, 3, 8, 8]
Output：true
Explanation：8 / ( 3 - 8 / 3) = 24
Notice
The division operator / represents real division, not integer division. so 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, 
with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12. */

public class Solution {
    /**
     * @param nums: 4 cards
     * @return: whether they could get the value of 24
     */
    public boolean compute24(double[] nums) {
        // write your code here
        return compute(nums, 4);
    }
    class Solution {
    public boolean judgePoint24(int[] nums) {
        double[] array = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = nums[i] + 0.0;
        }
        return compute(array, 4);
    }
    
    private boolean compute(double[] nums, int n) {
        if (n == 1) {
            if (Math.abs(nums[0] - 24) <= 1E-6) {   
                return true;    
            } 
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double a = nums[i];
                double b = nums[j];
                nums[j] = nums[n - 1];
                nums[i] = a + b;
                if (compute(nums, n - 1)) {
                    return true;
                }
                nums[i] = a - b;
                if (compute(nums, n - 1)) {
                    return true;
                }
                nums[i] = b - a;
                if (compute(nums, n - 1)) {
                    return true;
                }
                nums[i] = a * b;
                if (compute(nums, n - 1)) {
                    return true;
                }
                if (b != 0) {
                    nums[i] = a / b;
                    if (compute(nums, n - 1)) {
                        return true;
                    }
                }
                if (a != 0) {
                    nums[i] = b / a;
                    if (compute(nums, n - 1)) {
                        return true;
                    }
                }
                nums[i] = a;
                nums[j] = b;
            }
        }
        return false;
    }
}
}