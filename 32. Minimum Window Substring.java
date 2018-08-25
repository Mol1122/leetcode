public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        if (source == null || target == null || 
            source.length() == 0 || target.length() == 0) {
            return "";
        }
        char[] sc = source.toCharArray();
        char[] tc = target.toCharArray();
        int[] cntS = new int[256];
        int[] cntT = new int[256];
        initcntT(tc, cntT);
        int j = 0;
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        
        for (int i = 0; i < sc.length; i++) {
            while (j < sc.length && !valid(cntS, cntT)) {
                cntS[sc[j]]++;
                j++;
            }
            if (valid(cntS, cntT)) {
                if (ans > j - i) {
                    ans = j - i;
                    minStr = source.substring(i, j);
                }
            }
            cntS[sc[i]]--;
        }
        return minStr;
    }
    
    private boolean valid(int[] cntS, int[] cntT) {
        for (int i = 0; i < 256; i++) {
            if (cntT[i] > cntS[i]) {
                return false;
            }
        }
        return true;
    }
    
    private void initcntT(char[] tc, int[] cntT) {
        for (char c : tc) {
            cntT[c]++;
        }
    }
}