class Solution {
    public int trap(int[] heights) {
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
        
//         if (heights == null || heights.length == 0) {
//             return 0;
//         }
//         int[] left = new int[heights.length];
//         int[] right = new int[heights.length];
        
//         left[0] = heights[0];
//         right[heights.length - 1] = heights[heights.length - 1];
        
//         for (int i = 1; i < heights.length; i++) {
//             left[i] = Math.max(left[i - 1], heights[i]);
//         }
//         for (int i = heights.length - 2; i >= 0; i--) {
//             right[i] = Math.max(right[i + 1], heights[i]);
//         }
        
//         int sum = 0;
//         for (int i = 0; i < heights.length; i++) {
//             sum += Math.min(left[i], right[i]) - heights[i];
//         }
//         return sum;
//         if (height == null || height.length == 0) {
//             return 0;
//         }
//         int left = 0, right = height.length - 1;
//         int leftheight = height[left];
//         int rightheight = height[right];
//         int result = 0;
//         if (left >= right) {
//             return 0;
//         }
        
//         while (left < right) {
//             if (leftheight < rightheight) {
//                 left++;
//                 if (leftheight > height[left]) {
//                     result += leftheight - height[left];
//                 } else {
//                     leftheight = height[left];
//                 }
//             } else {
//                 right--;
//                 if (rightheight > height[right]) {
//                     result += rightheight - height[right];
//                 } else {
//                     rightheight = height[right];
//                 }
//             }
//         }
//         return result;
    }
}

/* 算法：双指针。从两边往内灌水，哪边低从哪边开始灌 
** 时间复杂度：O(n), space: O(1) */