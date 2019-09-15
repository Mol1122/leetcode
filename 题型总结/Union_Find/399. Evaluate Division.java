/* Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a 
real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
 where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].  */

class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //一开始，每个letter是自己的集合
        Map<String, String> root = new HashMap<>();
        Map<String, Double> map = new HashMap<>();
        
        for (int i = 0; i < equations.length; i++) {
            String s1 = equations[i][0], s2 = equations[i][1];
            if (!root.containsKey(s1)) {
                root.put(s1, s1);
                map.put(s1, 1.0);
            }
            if (!root.containsKey(s2)) {
                root.put(s2, s2);
                map.put(s2, 1.0);
            }
            
            String root1 = find(root, map, s1);
            String root2 = find(root, map, s2);
            
            root.put(root1, root2);
            map.put(root1, map.get(s2) * values[i] / map.get(s1)); //一个a等于几个b
        }
        double[] results = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String s1 = queries[i][0], s2 = queries[i][1];
            if (!root.containsKey(s1) || !root.containsKey(s2) ||
                !find(root, map, s1).equals(find(root, map, s2))) {
                results[i] = -1.0;
                continue;
            }
            results[i] = map.get(s1) / map.get(s2);
        }
        return results;
    }
    
    private String find(Map<String, String> root, 
                        Map<String, Double> map, 
                        String s) {
        if (root.get(s).equals(s)) {
            return s;
        }
        String prev = root.get(s);
        String p = find(root, map, prev);
        root.put(s, p);
        map.put(s, map.get(s) * map.get(prev));
        return p;
    }
}
/* 算法：有点类似于并查集, 分子作为root的好处在于a/b=2.0 a是b的两倍比b是a的二分之一好work on一些 */