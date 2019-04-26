public class Solution {
  public String alienOrder(String[] words) {
      if (words == null || words.length == 0) {
          return "";
      }
      Map<Character, List<Character>> graph = getGraph(words);
      Map<Character, Integer> indegree = getIndegree(graph);
    
      Queue<Character> queue = new LinkedList<>();
      for (char c : indegree.keySet()) {
          if (indegree.get(c) == 0) {
              queue.offer(c);
          }
      }
      StringBuilder sb = new StringBuilder();
      while (!queue.isEmpty()) {
          char c  = queue.poll();
          sb.append(c);
          for (char neighbor : graph.get(c)) {
              indegree.put(neighbor, indegree.get(neighbor) - 1);
              if (indegree.get(neighbor) == 0) {
                  queue.offer(neighbor);
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
//time: O(n*size of string), space: O(n * size of string)