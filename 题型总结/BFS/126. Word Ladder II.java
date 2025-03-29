class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        if (beginWord == null || endWord == null) {
            return results;
        }
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (!dict.contains(endWord))  {
            return results;
        }
        dict.add(beginWord);
        dict.add(endWord);
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        bfs(map, distance, beginWord, endWord, dict);
        
        List<String> path = new ArrayList<>();
        
        dfs(map, distance, endWord, beginWord, path, results);
        return results;
    }
    
    private void dfs(Map<String, List<String>> map, 
                     Map<String, Integer> distance, 
                     String curr, String start, 
                     List<String> path, List<List<String>> results) {
        path.add(curr);
        if (curr.equals(start)) {
            Collections.reverse(path);
            results.add(new ArrayList<>(path));
            Collections.reverse(path);
        } else {
            for (String next : map.get(curr)) {
                if (distance.containsKey(next) && distance.get(curr) == distance.get(next) + 1) {
                    dfs(map, distance, next, start, path, results);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    
    private void bfs(Map<String, List<String>> map, 
                     Map<String, Integer> distance, 
                     String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        distance.put(start, 0);
        
        for (String str : dict) {
            map.put(str, new ArrayList<>());
        }
        
        while (!q.isEmpty()) {
            String curr = q.poll();
            
            List<String> nextList = getNext(curr, dict);
            for (String next : nextList) {
                map.get(next).add(curr);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(curr) + 1);
                    q.offer(next);
                }
            }
        }
    }
    
    private List<String> getNext(String curr, Set<String> dict) {
        List<String> nextWords = new ArrayList<>();
        
        for (int i = 0; i < curr.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == curr.charAt(i)) {
                    continue;
                }
                String temp = curr.substring(0, i) + c + curr.substring(i + 1);
                if (dict.contains(temp)) {
                    nextWords.add(temp);
                }
            }
        }
        return nextWords;
    }
}

/* 算法：通过BFS求出从起点到每个点的距离，再通过DFS去求出从终点到起点的所有路径 */