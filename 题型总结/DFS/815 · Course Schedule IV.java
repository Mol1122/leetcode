/* There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the number of different ways you can get all the lessons

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


Example
Example 1

Input:
n = 2
prerequisites = [[1,0]]
Output: 1
Explantion:
You must have class in order 0->1.
Example 2

Input:
n = 2
prerequisites = []
Output: 2
Explanation:
You can have class in order 0->1 or 1->0. */

public class Solution {
    int result = 0;
    boolean[] done;
    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> indegree;

    public int topologicalSortNumber(int n, int[][] prerequisites) {
        graph = getGraph(prerequisites, n);
        indegree = getIndegree(graph);
        done = new boolean[n];

        dfs(0, n);
        return result;
    }

    private void dfs(int level, int n) {
        if (level == n) {
            result++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!done[i] && indegree.get(i) == 0) {
                done[i] = true;
                for (int neighbor : graph.get(i)) {
                    indegree.put(neighbor, indegree.get(neighbor) - 1);
                }
                dfs(level + 1, n);
                done[i] = false;
                for (int neighbor : graph.get(i)) {
                    indegree.put(neighbor, indegree.get(neighbor) + 1);
                }
            }
        }
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

    private Map<Integer, List<Integer>> getGraph(int[][] prerequisites, int n) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] course : prerequisites) {
            graph.get(course[1]).add(course[0]);
        }
        return graph;
    }
}