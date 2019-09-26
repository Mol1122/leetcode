/* Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation. */

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
    
    private void dfs(Map<String, List<String>> map, 
                     Map<String, Integer> distance, 
                     Set<String> dict,
                     String curr, String startWord, 
                     List<String> path, 
                     List<List<String>> results) {
        path.add(curr);
        if (curr.equals(startWord)) {
            Collections.reverse(path);
            results.add(new ArrayList<>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(curr)) {
                if (distance.containsKey(next) && distance.get(curr) == distance.get(next) + 1) {
                    dfs(map, distance, dict, next, startWord, path, results);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    
    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance, 
                     Set<String> dict, String start) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        
        for (String str : dict) {
            map.put(str, new ArrayList<>());
        }
        
        while (!queue.isEmpty()) {
            String str = queue.poll();
            
            List<String> nexts = getNexts(str, dict);
            for (String next : nexts) {
                map.get(next).add(str);
                if (!distance.containsKey(next)) {
                    queue.offer(next);
                    distance.put(next, distance.get(str) + 1);
                }
            }
        }
    }
    
    private List<String> getNexts(String word, Set<String> dict) {
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