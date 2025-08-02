/* Given an array of distinct strings words, return the minimal possible abbreviations for every word.

The following are the rules for a string abbreviation:

The initial abbreviation for each word is: the first character, then the number of characters in between, followed by the last character.
If more than one word shares the same abbreviation, then perform the following operation:
Increase the prefix (characters in the first part) of each of their abbreviations by 1.
For example, say you start with the words ["abcdef","abndef"] both initially abbreviated as "a4f". Then, a sequence of operations would be ["a4f","a4f"] -> ["ab3f","ab3f"] -> ["abc2f","abn2f"].
This operation is repeated until every abbreviation is unique.
At the end, if an abbreviation did not make a word shorter, then keep it as the original word.
 

Example 1:

Input: words = ["like","god","internal","me","internet","interval","intension","face","intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Example 2:

Input: words = ["aa","aaa"]
Output: ["aa","aaa"]          */

class Solution {
    public List<String> wordsAbbreviation(List<String> words) {
        List<String> results = new ArrayList<>();
        if (words == null || words.size() == 0) {
            return results;
        }
        List<Integer> index = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.size(); i++) {
            index.add(1);
            String ans = getAbbr(words.get(i), 1);
            results.add(ans);
            map.put(ans, map.getOrDefault(ans, 0) + 1);
        }
        while (true) {
            boolean unique = true;

            for (int i = 0; i < results.size(); i++) {
                if (map.get(results.get(i)) > 1) {
                    index.set(i, index.get(i) + 1);
                    String ans = getAbbr(words.get(i), index.get(i));
                    results.set(i, ans);
                    map.put(ans, map.getOrDefault(ans, 0) + 1);
                    unique = false;
                }
            }
            if (unique) {
                break;
            }
        }
        return results;
    }

    private String getAbbr(String str, int prefix) {
        if (prefix + 2 >= str.length()) {
            return str;
        }
        return str.substring(0, prefix) + (str.length() - 1 - prefix) + str.substring(str.length() - 1);
    }
}
/* 算法：比较复杂度纯模拟题。因为要判断是否有重复，所以用了map */