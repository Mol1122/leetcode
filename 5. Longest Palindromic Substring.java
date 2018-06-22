class Solution {
    int left = 0, max = 0;
    
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        int length = s.length();
        if (length < 2) {
            return s;
        }
        
        for (int i = 0; i < length - 1; i++) {
            extend(s, i, i); //assume the length is odd
            extend(s, i, i + 1); //assume the length is even
        }
        return s.substring(left, left + max);
    }
    private void extend(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        if (end - start + 1 > max) {
            max = end - start + 1;
            left = start++;
        }
    }
}

/* 算法：以每一个index为中心，看看最后能走到哪
** 难点：20行应该是while循环而不是if */