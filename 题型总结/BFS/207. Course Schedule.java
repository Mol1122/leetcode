/* There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible. */


//Method 1
class Solution {
    public boolean canFinish(int numCourses, int[][] preq) {
        if (preq == null || preq.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        
        for (int[] item : preq) {
            graph.putIfAbsent(item[1], new ArrayList<>());
            graph.get(item[1]).add(item[0]);
            indegree.putIfAbsent(item[0], 0);
            indegree.put(item[0], indegree.get(item[0]) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (!indegree.containsKey(i)) {
                queue.offer(i);        
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            if (!graph.containsKey(node)) {
                continue;
            }
            for (int neighbor : graph.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return count == numCourses;
    }
}
//time: O(V + E) 拓扑排序仅遍历点和边一次, space: O(V + E)

//Method 2
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graph = getGraph(prerequisites, numCourses);
        Map<Integer, Integer> indegree = getIndegree(graph);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int key : graph.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
                set.add(key);
            }
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int neighbor : graph.get(course)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0 && !set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        return count == numCourses; //这里也check了是否会有duplicate course, 如果有duplicate course, 数量会不对。eg. 0->1->2->1, 出来的count会是4
    }

    private Map<Integer, Integer> getIndegree(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> indegree = new HashMap<>();

        for (int key : graph.keySet()) {
            indegree.put(key, 0);
        }

        for (int key : graph.keySet()) {
            for (int neighbor : graph.get(key)) {
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            }
        }
        return indegree;
    }

    private Map<Integer, List<Integer>> getGraph(int[][] prerequisites, int numCourses) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] course : prerequisites) {
            graph.get(course[1]).add(course[0]);
        }
        return graph;
    }
}