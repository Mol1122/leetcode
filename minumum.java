public static String minWindow(String s, String t) {
        if (t == null || t.length() == 0) {
            return "";
        }
        int ans = Integer.MAX_VALUE;
        String minStr = "";

        if (t.length() == 1) {
            return s.charAt(0) + "";
        }
        int[] cntS = new int[256];
        int[] cntT = new int[256];
        initTargetHash(cntT, t);
        int i = 0, j = 0;
        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && !valid(cntS, cntT)) {
                cntS[s.charAt(j)]++;
                j++;
            }
            if (valid(cntS, cntT)) {
                if (ans > j - i) {
                    ans = j - i;
                    minStr = s.substring(i, j);
                    System.out.println(minStr);
                }
            }
            cntS[s.charAt(i)]--;
        }
        return minStr;
    }
    private static void initTargetHash(int[] cntT, String t) {
        for (char c : t.toCharArray()) {
            cntT[c]++;
        }
    }
    private static boolean valid(int[] cntS, int[] cntT) {
        int count = 0; //这里改动就好
        for (int i = 0; i < 256; i++) {
            if (cntT[i] > cntS[i]) {
                count++;
            }
        }
        //System.out.println(count);
        return count  <= 1;
    }
