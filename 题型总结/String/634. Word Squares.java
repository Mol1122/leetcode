/* Given a set of words without duplicates, find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Example
Example 1:

Input:
["area","lead","wall","lady","ball"]
Output:
[["wall","area","lead","lady"],["ball","area","lead","lady"]]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]
Output:
 [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
Notice
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z. */

public class Solution {
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> results = new ArrayList<>();
        if (words == null || words.length == 0) {
            return results;
        }
        Map<String, List<String>> hash = new HashMap<>();
        initHash(words, hash);
        dfs(0, words[0].length(), hash, new ArrayList<>(), results);
        return results;
    }
    
    private void dfs(int index, int wordLen, Map<String, List<String>> hash, 
                     List<String> square, List<List<String>> results) {
        if (index == wordLen) {
            results.add(new ArrayList<>(square));
            return;
        }
        String prefix = "";
        for (int i = 0; i < index; i++) {
            prefix += square.get(i).charAt(index);
        }
        for (String s : hash.get(prefix)) {
            if (!isValid(index, s, wordLen, hash, square, results)) {
                continue;
            }
            square.add(s);
            dfs(index + 1, wordLen, hash, square, results);
            square.remove(square.size() - 1);
        }
    }
    
    private boolean isValid(int index, String nextWord, int wordLen, 
                            Map<String, List<String>> hash, List<String> square, 
                            List<List<String>> results) {
        for (int i = index + 1; i < wordLen; i++) {
            String prefix = "";
            for (int k = 0; k < index; k++) {
                prefix += square.get(k).charAt(i);
            }
            prefix += nextWord.charAt(i);
            if (!hash.containsKey(prefix)) {
                return false;
            }
        }
        return true;
    }
    
    private void initHash(String[] words, Map<String, List<String>> hash) {
        for (String str : words) {
            hash.putIfAbsent("", new ArrayList<>());
            hash.get("").add(str);
            
            String prefix = "";
            for (char c : str.toCharArray()) {
                prefix += c;
                hash.putIfAbsent(prefix, new ArrayList<>());
                hash.get(prefix).add(str);
            }
        }
    }
}

/* 算法：本质上是个组合类dfs问题 */