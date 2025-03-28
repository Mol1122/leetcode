/* There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] . */


//Method 1
class Solution {
    public int[] findOrder(int numCourses, int[][] preq) {
        if (numCourses == 1) {
            return new int[]{0};
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
        int[] results = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            results[index++] = node;
            if (!graph.containsKey(node)) {
                continue;
            }
            for (int neighbor : graph.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    indegree.remove(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return index == numCourses ? results : new int[0];
    }
}
//time: O(V + E), space: O(V + E)

//Method 2
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> list = new ArrayList<>();
        if (prerequisites == null || prerequisites.length == 0) {
            int[] results = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                results[i] = i;
            }
            return results;
        }
        Map<Integer, List<Integer>> graph = getGraph(prerequisites, numCourses);
        Map<Integer, Integer> indegree = getIndegree(graph);
        Queue<Integer> queue = new LinkedList<>();

        for (int key : graph.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            list.add(node);

            for (int neighbor : graph.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        if (list.size() != numCourses) {
            return new int[0];
        }
        int[] results = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            results[i] = list.get(i);
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
            graph.put(i, new ArrayList<>());
        }
        for (int[] course : prerequisites) {
            graph.get(course[1]).add(course[0]);
        }
        return graph;
    }
}

//Method 3
public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List[] edges = new ArrayList[numCourses]; //the course is the prerequisite of what courses
        int[] degree = new int[numCourses]; //the course has how many # of prerequisites
        
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) { //has no prerequisite
                queue.offer(i); //add course number into queue
            }
        }
        int count = 0;
        int[] order = new int[numCourses];
        while (!queue.isEmpty()) {
            int course = (int)queue.poll();
            order[count++] = course;
            int n = edges[course].size();
            for (int i = n - 1; i >= 0; i--) {
                int pointer = (int)edges[course].get(i);
                degree[pointer]--;
                
                if (degree[pointer] == 0) { //has no prerequisite now
                    queue.offer(pointer);
                }
            }
        }
        if (count == numCourses) {
            return order;
        }
        return new int[0];
        
    }
}