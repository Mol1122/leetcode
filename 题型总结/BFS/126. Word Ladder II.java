/* A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.     */

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return results;
        }
        Set<String> dict = new HashSet<>();
        for (String str : wordList) {
            dict.add(str);
        }
        if (!dict.contains(endWord)) {
            return results;
        }
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        bfs(map, distance, dict, beginWord);
        
        List<String> path = new ArrayList<>();
        dfs(map, distance, dict, endWord, beginWord, path, results);
        
        return results;
    }
    
    private void dfs(Map<String, List<String>> map, Map<String, Integer> distance, Set<String> dict, String curr, String start, List<String> path, List<List<String>> results) {
        path.add(curr);
        
        if (curr.equals(start)) {
            Collections.reverse(path);
            results.add(new ArrayList<>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(curr)) {
                if (distance.containsKey(next) && distance.get(curr) == distance.get(next) + 1) {
                    dfs(map, distance, dict, next, start, path, results);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    
    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance, Set<String> dict, String start) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        
        for (String str : dict) {
            map.put(str, new ArrayList<>());
        }
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            
            List<String> nexts = getNext(curr, dict);
            for (String next : nexts) {
                map.get(next).add(curr);
                if (!distance.containsKey(next)) {
                    queue.offer(next);
                    distance.put(next, distance.get(curr) + 1);
                }
            }
        }
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