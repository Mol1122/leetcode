class Solution {
    public double[] calcEquation(String[][] equations, double[] values, 
                                 String[][] queries) {
        //一开始，每个letter自己是一个集合
        Map<String, String> root = new HashMap<String, String>(); 
        Map<String, Double> map = new HashMap<String, Double>();
        
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
            map.put(root1, map.get(s2) * values[i] / map.get(s1));
        }
        
        double[] res = new double[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            String s1 = queries[i][0], s2 = queries[i][1];
            if (!root.containsKey(s1) || !root.containsKey(s2) || 
                !find(root, map, s1).equals(find(root, map, s2))) {
                res[i] = -1.0;
                continue;
            }
            res[i] = map.get(s1) / map.get(s2);
        }
        
        return res;
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
        map.put(s, map.get(prev) * map.get(s));
        return p;
    }
}

/* 算法：有点类似于并查集 */