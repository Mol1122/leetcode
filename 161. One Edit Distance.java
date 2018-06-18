class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return s == t;
        }
        if (s.equals(t)) {
            return false;
        }
        int n = s.length();
        int m = t.length();
        if (Math.abs(n - m) > 1) {
            return false;
        }
        int len = Math.min(n, m);
        int index = 0;
        
        for (int i = 0; i < len; i++) {
            index++;
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(index).equals(t.substring(index)) ||
                       s.substring(index - 1).equals(t.substring(index)) ||
                       s.substring(index).equals(t.substring(index - 1));
            }
        }
        return true;
    }
}

/* 算法：如果两个字符串长度相差1以上，返回false，选取两个字符串短的那个，一一对应每位比较两个字符串，如果遇到不相等的index位，

则比较两个字符串的a, b的index+1, index+1 位以后是否相等或者 index+1, index 是否相等，或者index, index+1是否相等。

如果前面全都相等，说明只有最后一位不相等，那就返回true */