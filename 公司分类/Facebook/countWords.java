public static int countWords(String s, String t) {
        char[] tc = t.toCharArray();
        char[] sc = s.toCharArray();
        int[] cntT = new int[26];
        int[] cntS = new int[26];

        int result = 0;
        for (char c : tc) {
            cntT[c - 'a']++;
        }
        for (char c : sc) {
            cntS[c - 'a']++;
        }

        for (char c = 'a'; c <= 'z'; c++) {
            int count = 0;
            if (cntS[c - 'a'] == 0 && cntT[c - 'a'] > 0) {
                return -1;
            }
            while (cntT[c - 'a'] > 0) {
                count++;
                cntT[c - 'a'] -= cntS[c - 'a'];
            }
            result = Math.max(result, count);
        }
        return result;
}