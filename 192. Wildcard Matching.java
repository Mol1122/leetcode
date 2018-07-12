public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int i = 0, j = 0, starIndex = -1, iIndex = 0;
        
        while (i < s.length()) { //因为要遍历整个s，所以这里用s的长度做判断
            if ((j < p.length() && p.charAt(j) == '?') || (j < p.length() && p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) { //说明可以包含在*内的
                j = starIndex + 1; //准备检测*的下一个
                i = iIndex + 1; //跳过*
                iIndex++;
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }
}

/* 算法：用双指针去解决最简单。i遍历s, j遍历p
*  时间复杂度：O(n) */