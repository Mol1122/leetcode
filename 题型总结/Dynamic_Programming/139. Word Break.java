/* Given a string, can it be composed by concatenating words from a given dictionary
f[i] = whether str[0…i] can be split into words in the dict */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Set<String> dict = new HashSet<>();
        for (String str : wordDict) {
            dict.add(str);
        }
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        
        //左大段右小段思想
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) { //遍历左大段的长度,这也是startIndex of the right substring
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
//f[i] = 前i个char是否能由dict组成
//time: O(n^3), space: O(n)
