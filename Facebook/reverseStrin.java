public static String reverseString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        s = s.trim();
        String[] strs = s.split("\\s+");
        int n = strs.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = strs[i].charAt(strs[i].length() - 1);
            boolean hasC = false;
            if (!Character.isLetter(c)) {
                hasC = true;
            }

            int j = n - 1 - i;
            String temp = "";
            if (!Character.isLetter(strs[j].charAt(strs[j].length() - 1))) {
                temp = strs[j].substring(0, strs[j].length() - 1);
            } else {
                temp = strs[j];
            }
            sb.append(temp);
            if (hasC) {
                sb.append(c);
            }
            sb.append(" ");
        }
        return sb.toString();
}