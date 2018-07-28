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