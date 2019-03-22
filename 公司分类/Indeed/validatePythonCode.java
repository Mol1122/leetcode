public static boolean validatePythonCode(String[] strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        if (checkIndent(strs[0]) != 0) {
            return false;
        }
        stack.push(0);

        for (int i = 1; i < strs.length; i++) {
            String str = removeComment(strs[i]);
            if (str.equals("")) {
                continue;
            }
            int curr = checkIndent(str);

            if (strs[i - 1].charAt(strs[i - 1].length() - 1) == ':') {
                if (curr <= stack.peek()) {
                    return false;
                }
            } else {
                if (curr > stack.peek()) {
                    return false;
                }
                while (!stack.isEmpty() && curr < stack.peek()) {
                    stack.pop();
                }
                if (stack.peek() != curr) {
                    return false;
                }
            }
            stack.push(curr);
        }
        return true;
}

public static String removeComment(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '#') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
}

private static int checkIndent(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
}