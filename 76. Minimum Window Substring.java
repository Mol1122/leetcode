class Solution {
    public String minWindow(String s, String t) {
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        
        int[] cntS = new int[256];
        int[] cntT = new int[256];
        initTargetHash(cntT, t);
        int i = 0, j = 0;
        for (i = 0; i < s.length(); i++) {
            while (!valid(cntS, cntT) && j < s.length()) {
                cntS[s.charAt(j)]++;
                j++;
            }
            if (valid(cntS, cntT)) {
                if (ans > j - i) {
                    ans = Math.min(ans, j - i);
                    minStr = s.substring(i, j);
                }
            } 
            cntS[s.charAt(i)]--;
        }
        return minStr;
    }
    private void initTargetHash(int[] cntT, String t) {
        for (char c : t.toCharArray()) {
            cntT[c]++;
        }
    }
    private boolean valid(int[] cntS, int[] cntT) {
        for (int i = 0; i < 256; i++) {
            if (cntT[i] > cntS[i]) {
                return false;
            }
        }
        return true;
    }
}

/* 算法：窗口类型题，利用hash去计数 */