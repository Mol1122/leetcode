public class Solution {
    /**
     * @param str: a string
     * @return: a compressed string
     */
    public String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        
        while (i < str.length()) {
            int sum = 0;
            for (j = i; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    sum++;
                } else {
                    break;
                }
            }
            sb.append(str.charAt(i));
            sb.append(sum + "");
            i = j;
        }
        String result = sb.toString();
        if (result.length() >= str.length()) {
            return str;
        }
        return result;
    }
}