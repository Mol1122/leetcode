/* A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence. */

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(beginWord);
        set.add(beginWord);
        
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord)) {
                    return step;
                }
                List<String> nexts = getNext(curr, dict);
                for (String next : nexts) {
                    if (!set.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
            step++;
        }
        return 0;
    }
    
    private List<String> getNext(String word, Set<String> dict) {
        List<String> results = new ArrayList<>();
        
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                char[] sc = word.toCharArray();
                sc[i] = c;
                String next = new String(sc);
                if (dict.contains(next)) {
                    results.add(next);
                }
            }
        }
        return results;
    }
}