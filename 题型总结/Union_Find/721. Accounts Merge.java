/* Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */

//RECOMMEND
class UnionFind {
    int[] fathers;
    
    public UnionFind(int n) {
        fathers = new int[n];
        for (int i = 0; i < n; i++) {
            fathers[i] = i;
        }
    }
    
    public int find(int x) {
        if (fathers[x] == x) {
            return x;
        }
        
        return fathers[x] = find(fathers[x]);
    }
    
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            fathers[rootA] = rootB;
        }
    }
}


public class Solution {
    /**
     * @param accounts: List[List[str]]
     * @return: return a List[List[str]]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList<>();
        if (accounts == null) {
            return ans;
        }
        
        UnionFind uf = new UnionFind(accounts.size());
        
        Map<String, Integer> EmailsToID = new HashMap<>();
        
        //为了拿到一个email下对应的所有account,并且union起来
        for (int id = 0; id < accounts.size(); id++) {
            List<String> account = accounts.get(id);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                // union all the emails under the same person's name
                EmailsToID.putIfAbsent(email, id);
                uf.union(EmailsToID.get(email), id);
            }
        }
        
        //找到一个root account, 把属于这个集合的email都加上
        Map<Integer, List<String>> IDToEmailLists = new HashMap<>();
        for (String email : EmailsToID.keySet()) {
            int root = uf.find(EmailsToID.get(email));
            IDToEmailLists.putIfAbsent(root, new ArrayList<>()); //一个set只会开一个集合，就是以root为起点的
            IDToEmailLists.get(root).add(email);
        }
        
        //append result
        for (Integer id : IDToEmailLists.keySet()) {
            List<String> sortedEmails = IDToEmailLists.get(id);
            
            Collections.sort(sortedEmails);
            
            List<String> accountInfo = new ArrayList<>();
            accountInfo.add(accounts.get(id).get(0)); // add the name to the list
            accountInfo.addAll(sortedEmails); // add the all associated list
            ans.add(accountInfo);
        }
        
        return ans;
    }
}

//NOT RECOMMEND
class Solution {
    int[] father = null;
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> map = new HashMap<>(); //emial, index表示属于哪个集合
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
        for (int i = 0; i < father.length; i++) { //遍历root id
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




          
        