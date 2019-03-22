public static String addNumber(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        num1 += ".0";
        num2 += ".0";
        //System.out.println(num2);

        String[] strs1 = num1.split("\\.");
        String[] strs2 = num2.split("\\.");
        //System.out.println(strs2[0]);
        //System.out.println(strs2[1]);
        String num1Left = strs1[0];
        String num1Right = strs1[1];
        String num2Left = strs2[0];
        String num2Right = strs2[1];

        if (num1Right.length() != num2Right.length()) {
            int diff = Math.abs(num1Right.length() - num2Right.length());
            if (num1Right.length() < num2Right.length()) {
                while (diff > 0) {
                    num1Right += "0";
                    diff--;
                }
            } else {
                while (diff > 0) {
                    num2Right += "0";
                    diff--;
                }
            }
        }
        String ans = "";
        int carry = 0;
        for (int i = num1Right.length() - 1, j = num2Right.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += num1Right.charAt(i) - '0';
            sum += num2Right.charAt(j) - '0';
            ans = sum % 10 + ans;
            carry = sum / 10;
        }
        ans = "." + ans;

        for (int i = num1Left.length() - 1, j = num2Left.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? num1Left.charAt(i) - '0' : 0;
            sum += j >= 0 ? num2Left.charAt(j) - '0' : 0;
            ans = sum % 10 + ans;
            carry = sum / 10;
        }
        if (carry != 0) {
            ans = carry + ans;
        }
        return ans;
    }
