class Solution {
    int[] father = null;
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> map = new HashMap<>(); //emial, index
        int n = accounts.size();
        father = new int[n];
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
        
        for (int i = 0; i < accounts.size(); i++) {
            List<String> list = accounts.get(i);
            for (int j = 1; j < list.size(); j++) {
                if (map.containsKey(list.get(j))) {
                    union(i, map.get(list.get(j)));
                }
                map.put(list.get(j), i);
            }
        }
        
        List<List<String>> result = new ArrayList<List<String>>();
        boolean[] marked = new boolean[father.length];
        for (int i = 0; i < father.length; i++) {
            if (!marked[i]) {
                Set<String> set = new HashSet<>();
                for (int j = 0; j < father.length; j++) {
                    if (father[j] == i) {
                        marked[j] = true;
                        for (int k = 1; k < accounts.get(j).size(); k++) {
                            set.add(accounts.get(j).get(k));
                        }
                    }
                }
                if (set.size() == 0) {
                    continue;
                }
                List<String> l = new ArrayList<>();
                l.add(accounts.get(i).get(0));
                List<String> l2 = new ArrayList<>(set);
                Collections.sort(l2);
                l.addAll(l2);
                result.add(l);
            }
        }
        return result;
    } 
    
    private void union(int a, int b) {  
        int root_a = find(a), root_b = find(b);  
        for(int i = 0; i < father.length; i++)  
            if(father[i] == root_a) { //难点！！
                father[i] = root_b;
            }   
    }  
  
    private int find(int i) {  
        if (father[i] == i) {
            return i;
        } 
        return father[i] = find(father[i]);
    }  
}

/* 算法：Union Find, 每一个account都有一个index number，union是union相同email对应的index下的所有index（难点！！）
** 时间复杂度：O(n^3) */
