public static String addBold(String s, String target) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == target.charAt(0)) {
                if (s.substring(i, i + target.length()).equals(target) &&
                        (i + target.length() == s.length() ||
                                i + target.length() < s.length() && !Character.isLetter(s.charAt(i + target.length())))) {
                    sb.append("<b>");
                    sb.append(target);
                    sb.append("</b>");
                    i = i + target.length();
                    continue;
                }
            }
            sb.append(c);
            i++;
        }
        return sb.toString();
    }