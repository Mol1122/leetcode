public static Comparator<String> cmp = new Comparator<String>() { //O(n)
        public int compare(String a, String b) {
            int a_num = 0, b_num = 0;

            int i = 0, j = 0;
            while (i < a.length() && j < b.length()) {
                if (Character.isDigit(a.charAt(i)) && Character.isLetter(b.charAt(j))) {
                    return -1;
                }
                if (Character.isLetter(a.charAt(i)) && Character.isDigit(b.charAt(j))) {
                    return 1;
                }
                if (Character.isLetter(a.charAt(i)) && Character.isLetter(b.charAt(j))) {
                    if (a.charAt(i) == b.charAt(j)) {
                        i++;
                        j++;
                    } else {
                        return a.charAt(i) - b.charAt(j);
                    }
                }
                if (Character.isDigit(a.charAt(i)) && Character.isDigit(b.charAt(j))) {
                    while (i < a.length() && Character.isDigit(a.charAt(i))) {
                        a_num = a_num * 10 + a.charAt(i) - '0';
                        i++;
                    }
                    while (j < b.length() && Character.isDigit(b.charAt(j))) {
                        b_num = b_num * 10 + b.charAt(j) - '0';
                        j++;
                    }
                    if (a_num != b_num) {
                        return a_num - b_num;
                    } else {
                        a_num = 0;
                        b_num = 0;
                    }
                }
            }
            if (i < a.length()) {
                return 1;
            }
            if (j < b.length()) {
                return -1;
            }
            return 0;
        }
    };
