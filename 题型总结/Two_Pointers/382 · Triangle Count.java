/* Description
Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?

Example 1:

Input: [3, 4, 6, 7]
Output: 3
Explanation:
They are (3, 4, 6), 
         (3, 6, 7),
         (4, 6, 7)
Example 2:

Input: [4, 4, 4, 4]
Output: 4
Explanation:
Any three numbers can form a triangle. 
So the answer is C(3, 4) = 4              */

public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        if (S == null || S.length == 0) {
            return 0;
        }
        int ans = 0;
        Arrays.sort(S);
        
        for (int i = 2; i < S.length; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (S[left] + S[right] > S[i]) {
                    ans += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}

/* 算法：相向型双指针
** 时间复杂度：O(n^2) */