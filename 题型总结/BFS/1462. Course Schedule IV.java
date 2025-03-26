/* There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.

 

Example 1:


Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
Example 2:

Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.
Example 3:


Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
 */

 class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> results = new ArrayList<>();
        Map<Integer, List<Integer>> graph = getGraph(prerequisites, numCourses);
        Map<Integer, Integer> indegree = getIndegree(graph);
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>(); //node -> all prerequisites

        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                map.putIfAbsent(neighbor, new HashSet<>());
                map.get(neighbor).add(node);

                for (int pre : map.getOrDefault(node, new HashSet<>())) {
                    map.get(neighbor).add(pre);
                }

                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        for (int[] query : queries) {
            if (map.getOrDefault(query[1], new HashSet<>()).contains(query[0])) {
                results.add(true);
            } else {
                results.add(false);
            }
        }
        return results;
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
            graph.putIfAbsent(i, new ArrayList<>());
        }
        for (int[] course : prerequisites) {
            graph.get(course[0]).add(course[1]);
        }
        return graph;
    }
}
//time: O(|E| + |V|)