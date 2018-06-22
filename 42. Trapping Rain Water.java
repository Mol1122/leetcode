class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0, right = height.length - 1;
        int leftheight = height[left];
        int rightheight = height[right];
        int result = 0;
        if (left >= right) {
            return 0;
        }
        
        while (left < right) {
            if (height[left] < height[right]) {
                left++;
                if (leftheight > height[left]) {
                    result += leftheight - height[left];
                } else {
                    leftheight = height[left];
                }
            } else {
                right--;
                if (rightheight > height[right]) {
                    result += rightheight - height[right];
                } else {
                    rightheight = height[right];
                }
            }
        }
        return result;
    }
}

/* 算法：双指针。从两边往内灌水，哪边低从哪边开始灌 
** 时间复杂度：O(n) */