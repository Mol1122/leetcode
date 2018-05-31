class Solution {
    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        }
        int[] cnt = new int[256];
        for (char c : s.toCharArray()) {
            cnt[c]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
}
/* 利用数组去计数 */