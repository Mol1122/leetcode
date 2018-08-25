class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sc = s.toCharArray();
        Map<Character, Integer> hash = new HashMap<>();
        int j = 0;
        int max = 0;
        
        int n = sc.length;
        for (int i = 0; i < n; i++) {
            while (j < n && (hash.size() < k || hash.size() == k && hash.containsKey(sc[j]))) {
                hash.putIfAbsent(sc[j], 0);
                hash.put(sc[j], hash.get(sc[j]) + 1);
                j++;
            }
            max = Math.max(max, j - i);
            if (hash.containsKey(sc[i])) {
                hash.put(sc[i], hash.get(sc[i]) - 1);
                if (hash.get(sc[i]) == 0) {
                    hash.remove(sc[i]);
                }
            }
        }
        return max;
    }
}