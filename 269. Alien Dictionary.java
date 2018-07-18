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
        //因为如果相等，要返回lexilogical order
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