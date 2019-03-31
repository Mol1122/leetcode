public class Solution {
  public int maxTrapped(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int count = 0;
        int left = 0, right = heights.length -1;
        int leftHeight = heights[left], rightHeight = heights[right];
        
        while (left < right) {
            if (heights[left] <= heights[right]) {
                left++;
                if (heights[left] < leftHeight) {
                    count += leftHeight - heights[left];
                } else {
                    leftHeight = heights[left];    
                }
            } else {
                right--;
                if (heights[right] < rightHeight) {
                    count += rightHeight - heights[right];
                } else {
                    rightHeight = heights[right];    
                }
            }
        }
        return count;
  }
}
