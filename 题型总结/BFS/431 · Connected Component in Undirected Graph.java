/* Description
Find connected component in undirected graph.

Each node in the graph contains a label and a list of its neighbors.

(A connected component of an undirected graph is a subgraph in which any two vertices are connected to each other by paths, and which is connected to no additional vertices in the supergraph.)

You need return a list of label set. 

Example 1:

Input: {1,2,4#2,1,4#3,5#4,1,2#5,3}
Output: [[1,2,4],[3,5]]
Explanation:

  1------2  3
   \     |  | 
    \    |  |
     \   |  |
      \  |  |
        4   5
Example 2:

Input: {1,2#2,1}
Output: [[1,2]]
Explanation:

  1--2           */

  /**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

//Method 1  
class UnionFind {
    HashMap<Integer, Integer> father = null;
    
    public UnionFind(HashSet<Integer> hashSet) {
        father = new HashMap<Integer, Integer>();
        for (int i : hashSet) {
            father.put(i, i);
        }
    }
    
    public int find(int x) {
        int parent = father.get(x);
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        return parent;
    }
    
    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father.put(root_a, root_b);
        }
    }
}

public class Solution {
    /*
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        HashSet<Integer> hashSet = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        
        for (UndirectedGraphNode n : nodes) {
            hashSet.add(n.label);
            for (UndirectedGraphNode ne : n.neighbors) {
                hashSet.add(ne.label);
            }
        }
        UnionFind uf = new UnionFind(hashSet);
        
        for (UndirectedGraphNode n : nodes) {
            for (UndirectedGraphNode ne : n.neighbors) {
                int root_a = uf.find(n.label);
                int root_b = uf.find(ne.label);
                if (root_a != root_b) {
                    uf.union(n.label, ne.label);
                }
            }
        }
        connectedSetHelper(hashSet, uf, ans);
        return ans;
    }
    private void connectedSetHelper(HashSet<Integer> hashSet, UnionFind uf, List<List<Integer>> ans) {
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i : hashSet) {
            int fa = uf.find(i);
            if (!hashMap.containsKey(fa)) {
                hashMap.put(fa, new ArrayList<>());
            }
            List<Integer> now = hashMap.get(fa);
            now.add(i);
            hashMap.put(fa, now);
        }
        
        for (List<Integer> i : hashMap.values()) {
            Collections.sort(i);
            ans.add(i);
        }
    }
}


//Method 2
/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>();
 *     }
 * }
 */

public class Solution {
    /**
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> results = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) {
            return results;
        }
        Set<UndirectedGraphNode> set = new HashSet<>();

        for (UndirectedGraphNode node : nodes) {
            if (!set.contains(node)) {
                bfs(node, nodes, set, results);
            }
        }
        return results;
    }

    private void bfs(UndirectedGraphNode root, List<UndirectedGraphNode> nodes, Set<UndirectedGraphNode> set, List<List<Integer>> results) {
        List<Integer> list = new ArrayList<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(root);
        set.add(root);

        while (!queue.isEmpty()) {
            UndirectedGraphNode node = queue.poll();
            list.add(node.label);

            for (UndirectedGraphNode neighbor : node.neighbors) {
                if (!set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        Collections.sort(list);
        results.add(list);
    }
}