/* There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are sorted lexicographically by the rules of this new language.

If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.

 

Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "". */

//Method 1
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, Set<Character>> graph = constructGraph(words);
        
        String result = topologicalSorting(graph);
        
        return result;
    }
    
    private Map<Character, Set<Character>> constructGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        //create nodes
        for (String str : words) {
            for (char c : str.toCharArray()) {
                if (!graph.containsKey(c)) {
                    graph.put(c, new HashSet<>());
                }
            }
        }
        
        //create edges
        int index = 0; // from left to right , 从最上两行开始往下走
        for (int i = 0; i < words.length - 1; i++) {
            index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    graph.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break;
                }
                index++;
            }
        }
        return graph;
    }
    
    private String topologicalSorting(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = getInDegree(graph);
        //因为如果相等，要返回lexilogical order. 这里可能有多个有效的字母顺序，返回以正常字典顺序看来最小的
        Queue<Character> queue = new PriorityQueue<>();
        
        for (char c: indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        StringBuilder sb = new StringBuilder();
    
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char next : graph.get(c)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0){
                    queue.offer(next);
                }
            }
        }
        if (sb.length() != indegree.size()) { //易漏
            return "";
        }
        return sb.toString();
    }
    
    private Map<Character, Integer> getInDegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        
        for (Character c : graph.keySet()) {
            indegree.put(c, 0);
        }
        for (char c : graph.keySet()) {
            for (char v : graph.get(c)) {
                indegree.put(v, indegree.get(v) + 1);
            }
        }
        return indegree;
    }
}

/* 算法：拓扑排序 1.如何创建     */

//Method 2
class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        Map<Character, List<Character>> graph = getGraph(words);
        if (graph == null) {
            return "";
        }
        Map<Character, Integer> indegree = getIndegree(graph);
        Queue<Character> queue = new LinkedList<>(); //in leetcode, we can return any solution, not necessarily normal lexicographical order

        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }
        String s = "";
        while (!queue.isEmpty()) {
            char c = queue.poll();
            s += c;
            for (char neighbor : graph.get(c)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return s.length() == graph.size() ? s : "";
    }

    private Map<Character, Integer> getIndegree(Map<Character, List<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();

        for (char c : graph.keySet()) {
            indegree.put(c, 0);
        }
        for (char c : graph.keySet()) {
            for (char neighbor : graph.get(c)) {
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            }
        }
        return indegree;
    }

    private Map<Character, List<Character>> getGraph(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new ArrayList<>());
            }
        }

        //要记住，在正常sort的时候，ab是排在abc之前的。所以如果input是[abc, ab], 说明这个排序是invalid的
        for (int i = 0; i < words.length - 1; i++) {
            int j = 0;
            while (j < words[i].length() && j < words[i + 1].length()) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    graph.get(words[i].charAt(j)).add(words[i + 1].charAt(j));
                    break;
                }
                j++;
            }
            if (j == words[i].length() || j == words[i + 1].length()) {
                if (words[i].length() > words[i + 1].length()) {
                    return null;
                }
            }
        }
        return graph;
    }
}
//time: O(V + E), space: O(V)