/* Validate if a given string can be interpreted as a decimal number.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
" -90e3   " => true
" 1e" => false
"e3" => false
" 6e-1" => true
" 99e2.5 " => false
"53.5e93" => true
" --6 " => false
"-+3" => false
"95a54e53" => false

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements 
up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

Numbers 0-9
Exponent - "e"
Positive/negative sign - "+"/"-"
Decimal point - "."
Of course, the context of these characters also matters in the input. */

class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim() ;
        if (s.length() == 0) {
            return false;
        }
        char[] sc = s.toCharArray();
        int n = sc.length;
        
        int i = 0;
        if (sc[i] == '+' || sc[i] == '-') {
            i++;
        }
        int nNum = 0, nPoint = 0;
        while (i < n && (Character.isDigit(sc[i]) || sc[i] == '.')) {
            if (Character.isDigit(sc[i])) {
                nNum++;
            }
            if (sc[i] == '.') {
                nPoint++;
            }
            i++;
        }
        
        if (nNum <= 0 || nPoint > 1) {
            return false;
        }
        if (i == n) {
            return true;
        }
        if (sc[i] == 'e') {
            i++;
            if (i == n) {
                return false;
            }
            if (sc[i] == '+' || sc[i] == '-') {
                i++;
            }
            if (i >= n) {
                return false;
            }
            for (; i < n; i++) {
                if (!Character.isDigit(sc[i])) {
                    return false;
                }
            }
        }
        
        return i == n;
    }
}
//time: O(n), sapce: O(1)