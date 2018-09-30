public class Solution {
    /**
     * @param S: a string
     * @return: return a list of strings
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        if (S == null) {
            return res;
        }
        helper(S, 0, res);
        return res;
    }
    
    private void helper(String S, int startIndex, List<String> res) {
        res.add(S);
        for (int i = startIndex; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) {
                continue;
            }
            if (S.charAt(i) - 'a' >= 0) {
                //lowercase
                //can be turned to uppercase
                helper(S.substring(0, i) + (char)(S.charAt(i) - 'a' + 'A') + S.substring(i + 1), i + 1, res);
            } else {
                //uppercase
                //can be turned to lowercase
                helper(S.substring(0, i) + (char)(S.charAt(i) - 'A' + 'a') + S.substring(i + 1), i + 1, res);
            }
        }
    }
}