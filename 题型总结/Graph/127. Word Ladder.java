/* Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation. */

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return -1;
        }
        Set<String> dict = new HashSet<>();
        for (String s : wordList) {
            dict.add(s);
        }
        if (beginWord.equals(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(beginWord);
        hash.add(beginWord);
        
        int step = 1;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                List<String> nextWords = getNexts(curr, dict);
                
                for (String next : nextWords) {
                    if (hash.contains(next)) {
                        continue;
                    }
                    if (next.equals(endWord)) {
                        return step;
                    }
                    queue.offer(next);
                    hash.add(next);
                }
            }
        }
        return 0;
    }
    
    private List<String> getNexts(String s, Set<String> dict) {
        List<String> results = new ArrayList<>();
        
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < s.length(); i++) {
                if (c == s.charAt(i)) {
                    continue;
                }
                String next = getNext(s, i, c);
                if (dict.contains(next)) {
                    results.add(next);
                }
            }
        }
        return results;
    }
    
    private String getNext(String s, int index, char c) {
        char[] sc = s.toCharArray();
        sc[index] = c;
        return new String(sc);
    }
}