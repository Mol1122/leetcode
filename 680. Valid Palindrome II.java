class Solution {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left++;
            right--;
            if (left >= right) {
                return true;
            }
        }
        return helper(s, left+1, right) || helper(s, left, right-1);
    }
    
    private boolean helper(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/* 算法：双指针
** 时间复杂度：O(n) */