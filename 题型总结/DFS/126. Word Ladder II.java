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
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence. */

//Method 1
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return results;
        }
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (!dict.contains(endWord)) {
            return results;
        }
        //dict.add(beginWord);

        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        bfs(map, distance, dict, beginWord); //find the shortest distance
        dfs(map, distance, dict, endWord, beginWord, new ArrayList<>(), results); //find all the paths

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

    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance, Set<String> dict, String begin) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);
        distance.put(begin, 0);

        for (String s : dict) {
            map.put(s, new ArrayList<>());
        }

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> nexts = getNext(curr, dict);

            for (String next : nexts) {
                map.get(next).add(curr); //因为不止curr一条路径可以到next, 所以不能放到!distance.containsKey(next)里面
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

//Method 2
class Solution {
    public List<List<String>> findLadders(String begin, String end, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if (begin == null || end == null || wordList == null || wordList.size() == 0) {
            return results;
        }
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        //dict.add(begin);
        if (!dict.contains(end)) {
            return results;
        }
        Map<String, List<String>> map = new HashMap<>(); //curr -> a list of prev strings can get/transform to curr
        Map<String, Integer> distance = new HashMap<>(); //curr -> how many steps that can get to string curr


        //find the shortest distance from begin to end
        bfs(map, distance, dict, begin);

        //find all the paths from end to begin. 因为从distance.get(end)知道到end的shortest distance是多少
        List<String> path = new ArrayList<>();
        path.add(end);
        dfs(map, distance, dict, end, begin, path, results);
        path.remove(path.size() - 1);
        return results;
    }

    private void dfs(Map<String, List<String>> map, Map<String, Integer> distance, Set<String> dict, String curr, String begin, List<String> path, List<List<String>> results) {
        if (curr.equals(begin)) {
            Collections.reverse(path);
            results.add(new ArrayList<>(path));
            Collections.reverse(path);
        } else {
            for (String prev : map.get(curr)) {
                if (distance.containsKey(prev) && distance.get(curr) == distance.get(prev) + 1) {
                    path.add(prev);
                    dfs(map, distance, dict, prev, begin, path, results);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    private void bfs(Map<String, List<String>> map, Map<String, Integer> distance, Set<String> dict, String begin) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(begin);
        distance.put(begin, 0);

        for (String word : dict) {
            map.put(word, new ArrayList<>());
        }
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> nexts = getNext(dict, curr);
            
            for (String next : nexts) {
                map.get(next).add(curr);
                if (!distance.containsKey(next)) {
                    queue.offer(next);
                    distance.put(next, distance.get(curr) + 1);
                }
            }
        }
    }

    private List<String> getNext(Set<String> dict, String curr) {
        List<String> results = new ArrayList<>();

        for (int i = 0; i < curr.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (curr.charAt(i) == c) {
                    continue;
                }
                char[] sc = curr.toCharArray();
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