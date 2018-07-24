/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    int n = 0;
    Map<String, Integer> id = new HashMap<>();
    public List<Connection> lowestCost(List<Connection> connections) {
        List<Connection> results = new ArrayList<>();
        if (connections == null || connections.size() == 0) {
            return results;
        }
        UnionFind uf = new UnionFind(connections.size() * 2);
        
        Collections.sort(connections, new Comparator<Connection>() {
            public int compare(Connection a, Connection b) {
                if (a.cost != b.cost) {
                    return a.cost - b.cost;
                }
                if (a.city1.equals(b.city1)) {
                    return a.city2.compareTo(b.city2);
                }
                return a.city1.compareTo(b.city1);
            }
        }); 
        
        for (Connection c : connections) {
            int id1 = getID(c.city1);
            int id2 = getID(c.city2);
            int root_city1 = uf.find(id1);
            int root_city2 = uf.find(id2);
            if (root_city1 != root_city2) {
                results.add(c);
                uf.union(id1, id2);
            }
        }
        
        if (results.size() == n - 1) {
            return results;
        }
        return new ArrayList<>();
    }
    
    private int getID(String s) {
        if (id.containsKey(s)) {
            return id.get(s);
        }
        id.put(s, n++);
        return n - 1;
    }
}


class UnionFind {
    private int[] father = null;
    
    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i]= i;
        }
    }
    
    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a != root_b) {
            father[root_a] = root_b;
        }
    }
}