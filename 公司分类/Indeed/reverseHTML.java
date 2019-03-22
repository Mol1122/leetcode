public static String reverseHTML(String s) {
        List<String> list = new ArrayList<>();
        int i = 0, start = 0;
        boolean flag = false;

        while (i < s.length()) {
            if (s.charAt(i) == '&') {
                list.add(reverse(s.substring(start, i)));
                start = i;
                flag = true;
            } else if (s.charAt(i) == ';' && flag) {
                list.add(s.substring(start, i + 1));
                start = i + 1;
                //i++;
                flag = false;
            }
            i++;
        }
        list.add(reverse(s.substring(start, s.length())));

        StringBuilder sb = new StringBuilder();
        for (int index = list.size() - 1; index >= 0; index--) {
            sb.append(list.get(index));
        }
        return sb.toString();
}

private static String reverse(String s) {
        int i = 0, j = s.length() - 1;
        char[] sc = s.toCharArray();

        while (i < j) {
            char c = sc[i];
            sc[i] = sc[j];
            sc[j] = c;
            i++;
            j--;
        }
        return new String(sc);
}