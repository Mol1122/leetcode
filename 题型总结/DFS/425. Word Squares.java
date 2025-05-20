/* Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.

A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 

Example 1:

Input: words = ["area","lead","wall","lady","ball"]
Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input: words = ["abat","baba","atan","atal"]
Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters). */
 
class Solution {
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
            if (!isValid(index, s, wordLen, hash, square)) {
                continue;
            }
            square.add(s);
            dfs(index + 1, wordLen, hash, square, results);
            square.remove(square.size() - 1);
        }
    }
    private boolean isValid(int index, String nextWord, int wordLen, Map<String, List<String>> hash, List<String> square) {
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
        for (String word : words) {
            hash.putIfAbsent("", new ArrayList<>());
            hash.get("").add(word);
            
            String prefix = "";
            for (char c : word.toCharArray()) {
                prefix += c;
                hash.putIfAbsent(prefix, new ArrayList<>());
                hash.get(prefix).add(word);
            }
        }
    }
}

/* 算法：搜索类DFS
** 难点：pruing 两个地方*/