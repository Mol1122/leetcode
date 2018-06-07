class Solution {
    public boolean isNumber(String s) {
        int i = 0;
        s = s.trim() + " ";
        char[] sc = s.toCharArray();
        int len = s.length() - 1;
        
        if (sc[i] == '+' || sc[i] == '-') {
            i++;
        }
        int nDigits = 0, nPoints = 0;
        while (Character.isDigit(sc[i]) || sc[i] == '.') {
            if (Character.isDigit(sc[i])) {
                nDigits++;
            }
            if (sc[i] == '.') {
                nPoints++;
            }
            i++;
        }
        if (nDigits <= 0 || nPoints > 1) {
            return false;
        }
        if (sc[i] == 'e') {
            i++;
            if (sc[i] == '+' || sc[i] == '-') {
                i++;
            }
            if (i == len) {
                return false;
            }
            for (; i < len; i++) {
                if (!Character.isDigit(sc[i])) {
                    return false;
                }
            }
        }
        return i == len;
    }
}