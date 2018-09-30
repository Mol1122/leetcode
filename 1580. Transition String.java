public class Solution {
    /**
     * @param startString: 
     * @param endString: 
     * @return: Return true or false
     */
    public boolean canTransfer(String startString, String endString) {
        if (startString == null || endString == null || 
            startString.length() != endString.length()) {
                return false;
        }
        char[] sc = startString.toCharArray();
        char[] ec = endString.toCharArray();
        int[] cnt = new int[26];
        Map<Character, List<Integer>> charToIndex = new HashMap<>();
        
        for (int i = 0; i < sc.length; i++) {
            cnt[sc[i] - 'a']++;
            charToIndex.putIfAbsent(sc[i], new ArrayList<>());
            charToIndex.get(sc[i]).add(i);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (!charToIndex.containsKey(c)) {
                continue;
            }
            char cc = ec[charToIndex.get(c).get(0)];
            for (int i = 1; i < charToIndex.get(c).size(); i++) {
                if (ec[charToIndex.get(c).get(i)] != cc) {
                    return false;
                }
            }
        }
        boolean isPermutation = true;
        for (int i = 0; i < ec.length; i++) {
            cnt[ec[i] - 'a']--;
        }
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] != 0) {
                isPermutation = false;
            }
        }
        return !isPermutation;
    }
}
/* 算法： hash的应用。
** 难点： 判断的三个条件：长度不一样一定是false; 如果是permutation一定是false，然后相同的字母在A位置，在B中同样的位置也一要是相同的字母 */