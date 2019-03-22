// a--adbbbc -> +++++d+++c
    public static String changePlus(String s) { //time:O(n)
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] sc = s.toCharArray();
        int cut = 0;
        char prev = '*';
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == '-') {
                continue;
            }
            if (sc[i] == prev) {
                fill(sc,cut,i);
                cut = i + 1;
            } else {
                cut = i;
                prev = sc[i];
            }
        }
        return String.valueOf(sc);
//        if (s == null || s.length() == 0) {
//            return s;
//        }
//        int i = 0, j = 0;
//        StringBuilder sb = new StringBuilder();
//        while (i < s.length()) {
//            j = i;
//            if (j + 1 < s.length() && s.charAt(j + 1) == '-') {
//                while (j + 1 < s.length() && s.charAt(j + 1) == '-') {
//                    j++;
//                }
//                if (j + 1 < s.length() && s.charAt(j + 1) == s.charAt(i)) {
//                    for (int k = 0; k < j + 1 - i + 1; k++) {
//                        sb.append("+");
//                    }
//                    j = j + 2;
//                    i = j;
//                } else {
//                    while (i <= j) {
//                        sb.append(s.charAt(i));
//                        i++;
//                    }
//                }
//                continue;
//            }
//
//            if (j + 1 < s.length() && s.charAt(j + 1) == s.charAt(i)) {
//                while (j + 1 < s.length() && s.charAt(j + 1) == s.charAt(i)) {
//                    j++;
//                }
//                for (int k = 0; k < j - i + 1; k++) {
//                    sb.append("+");
//                }
//                j = j + 1;
//                i = j;
//                continue;
//            }
//            sb.append(s.charAt(i));
//            i++;
//        }
//        return sb.toString();
    }

    private static void fill(char[] letters, int start, int end) {
        for (int i = start; i <= end; i++) {
            letters[i] = '+';
        }
    }