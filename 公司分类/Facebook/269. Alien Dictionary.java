/* There is a new alien language which uses the latin alphabet. However, the order among 
letters are unknown to you. You receive a list of non-empty words from the dictionary, 
where words are sorted lexicographically by the rules of this new language. Derive the 
order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "". */

class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, List<Character>> graph = getGraph(words);
        Map<Character, Integer> indegree = getIndegree(graph);
        
        Queue<Character> queue = new PriorityQueue<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            List<Character> nexts = graph.get(c);
            for (char next : nexts) {
                indegree.put(next, indegree.get(next) - 1);
                
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        if (sb.length() == graph.keySet().size()) {
            return sb.toString();
        }
        return "";
    }
    
    private Map<Character, Integer> getIndegree(Map<Character, List<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        for (char c : graph.keySet()) {
            indegree.put(c, 0);
        }
        for (char key : graph.keySet()) {
            for (char c : graph.get(key)) {
                indegree.put(c, indegree.get(c) + 1);
            }
        }
        return indegree;
    }
    
    private Map<Character, List<Character>> getGraph(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        
        for (String s : words) {
            for (char c : s.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length() && j < words[i + 1].length(); j++) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    graph.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
                    break;
                }
            }
        }
        return graph;
    }
}