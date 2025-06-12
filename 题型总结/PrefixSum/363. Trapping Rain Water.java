/* Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9 */

//Method 1: prefix sum
public class Solution {
    /**
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        
        left[0] = heights[0];
        right[heights.length - 1] = heights[heights.length - 1];
        
        for (int i = 1; i < heights.length; i++) {
            left[i] = Math.max(left[i - 1], heights[i]);
        }
        for (int i = heights.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], heights[i]);
        }
        
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            sum += Math.min(left[i], right[i]) - heights[i];
        }
        return sum;
    }
}

/* 每个位置上的盛水数目 = min(左侧最高，右侧最高) - 当前高度

从左到右扫描一边数组，获得每个位置往左这一段的最大值，再从右到左扫描一次获得每个位置向右的最大值。
然后最后再扫描一次数组，计算每个位置上的盛水数目。

时间复杂度 O(n)，空间复杂度 O(n)
*/

//Method 2: two pointers
class Solution {
    public int trap(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int left = 0, right = heights.length - 1;
        int leftHeight = heights[left], rightHeight = heights[right];
        int sum = 0;
        
        while (left < right) {
            if (leftHeight < rightHeight) {
                left++;
                if (heights[left] < leftHeight) {
                    sum += leftHeight - heights[left];
                } else {
                    leftHeight = heights[left];
                }
            } else {
                right--;
                if (heights[right] < rightHeight) {
                    sum += rightHeight - heights[right];
                } else {
                    rightHeight = heights[right];
                }
            }
        }
        return sum;
    }
}
