class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
        	return true;
        }
        s = s.toLowerCase();
        int front = 0;
        int end = s.length() - 1;
        
        while (front < end) {
        	while (front < s.length() && !isValidChar(s.charAt(front))) {
        		front++;
        	}
        	if (front == s.length()) {
        		return true;
        	}
        	while (end >= 0 && !isValidChar(s.charAt(end))) {
        		end--;
        	}
        	if (end < 0) {
        		return true;
        	}
        	if (s.charAt(front) != s.charAt(end)) {
        		return false;
        	} else {
        		front++;
        		end--;
        	}
        }
        return true;
    }
    
    private boolean isValidChar(char c) {
		return Character.isLetter(c) || Character.isDigit(c);
	}
}

/* 算法：从两边开始，skip 空格，然后判断
** 难点：Character.isLetter(c) 
** 时间复杂度：O(n) */