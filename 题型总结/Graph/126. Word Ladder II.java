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
        if (beginWord == null || endWord == null) {
            return results;
        }
        Set<String> dict = new HashSet<>();
        for (String str : wordList) {
            dict.add(str);
        }
        if (!dict.contains(endWord)) {
            return results;
        }
        
        dict.add(beginWord);
        dict.add(endWord);
        
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        bfs(map, distance, dict, beginWord);
        
        dfs(map, distance, endWord, beginWord, new ArrayList<>(), dict, results);
        return results;
    }
    
    private void dfs(Map<String, List<String>> map, 
                     Map<String, Integer> distance, 
                     String curr, String start,
                     List<String> path,
                     Set<String> dict, 
                     List<List<String>> results) {
        //add to potential result
        path.add(curr);
        //try each possible string
        if (curr.equals(start)) {
            Collections.reverse(path);
            results.add(new ArrayList<>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(curr)) {
                if (distance.containsKey(next) && distance.get(curr) == distance.get(next) + 1) {
                    dfs(map, distance, next, start, path, dict, results);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    
    private void bfs(Map<String, List<String>> map, 
                     Map<String, Integer> distance, Set<String> dict, String start) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        for (String str : dict) {
            map.put(str, new ArrayList<>());
        }
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            
            List<String> nextWords = getNextWords(curr, dict);
            for (String next : nextWords) {
                map.get(next).add(curr);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(curr) + 1);
                    queue.offer(next);
                }
            }
        }
    }
    
    private List<String> getNextWords(String s, Set<String> dict) {
        List<String> results = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (s.charAt(i) == c) {
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

/* 算法：通过BFS求出从起点到每个点的距离，再通过DFS去求出从终点到起点的所有路径 */