public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sc = s.toCharArray();
        int max = 0;
        int j = 0;
        Set<Character> hash = new HashSet<>();
        
        for (int i = 0; i < sc.length; i++) {
            while (j < sc.length && !hash.contains(sc[j])) {
                hash.add(sc[j]);
                j++;
            }
            max = Math.max(max, j - i);
            hash.remove(sc[i]);
        }
        return max;
    }
}

/* 算法：同向型双指针
** 时间复杂度：O(n) */