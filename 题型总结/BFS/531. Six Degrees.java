/* Description
Six degrees of separation is a philosophical problem, which means that everyone and everything can be connected through six steps or less.

Now give you a friendship, calculate how many steps two people can be connected through, if not, return -1. Example1

Input: {1,2,3#2,1,4#3,1,4#4,2,3} and s = 1, t = 4 
Output: 2
Explanation:
    1------2-----4
     \          /
      \        /
       \--3--/
Example2

Input: {1#2,4#3,4#4,2,3} and s = 1, t = 4
Output: -1
Explanation:
    1      2-----4
                 /
               /
              3

*/

/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * };
 */


public class Solution {
    /*
     * @param graph: a list of Undirected graph node
     * @param s: Undirected graph node
     * @param t: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
        if (graph == null || graph.size() == 0 || s == null || t == null) {
            return -1;
        }
        if (s.label == t.label) {
            return 0;
        }
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(s);
        set.add(s);
        
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode c = queue.poll();
                for (UndirectedGraphNode neighbor : c.neighbors) {
                    if (neighbor.label == t.label) {
                        return steps;
                    }
                    if (!set.contains(neighbor)) {
                        queue.offer(neighbor);
                        set.add(neighbor);
                    }
                }
            }
        }
        return -1;
    }
}

/* 算法：图上的最短距离
** 时间复杂度：O(V+E)*/