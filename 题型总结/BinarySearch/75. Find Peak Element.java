/* There is an integer array which has the following features:

The numbers in adjacent positions are different.
A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
We define a position P is a peak if:

A[P] > A[P-1] && A[P] > A[P+1]
Find a peak element in this array. Return the index of the peak.

Example
Example 1:
    Input:  [1, 2, 1, 3, 4, 5, 7, 6]
    Output:  1 or 6
    
    Explanation:
    return the index of peek.


Example 2:
    Input: [1,2,3,4,1]
    Output:  3

Challenge
Time complexity O(logN)

Notice
It's guaranteed the array has at least one peak.
The array may contain multiple peeks, find any of them.
The array has at least 3 numbers in it. */

public class Solution {
    public int findPeak(int[] nums) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        int start = 1, end = nums.length - 2; //因为限定是在0, n - 1, 所以potencial solution
            //是1, n - 2之前。这是跟local minimum的不同之处
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] < nums[mid - 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] > nums[start - 1] && nums[start] > nums[start + 1]) {
            return start;
        }
        return end;
    }
}
//time: O(logn), space: O(1)