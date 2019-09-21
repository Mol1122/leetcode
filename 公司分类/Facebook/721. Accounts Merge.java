/* Given a list accounts, each element accounts[i] is a list of strings, where the first element 
accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if 
there is some email that is common to both accounts. Note that even if two accounts have the 
same name, they may belong to different people as people could have the same name. A person can
have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of 
each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], 
["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  
["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], 
['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted. */

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> results = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) {
            return results;
        }
        UnionFind uf = new UnionFind(accounts.size());
        
        Map<String, Integer> email2id = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                email2id.putIfAbsent(email, i);
                uf.union(email2id.get(email), i);
            }
        }
        Map<Integer, List<String>> id2emails = new HashMap<>();
        for (String email : email2id.keySet()) {
            int root = uf.find(email2id.get(email));
            id2emails.putIfAbsent(root, new ArrayList<>());
            id2emails.get(root).add(email);
        }
        for (int id : id2emails.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(id).get(0));
            List<String> temp = id2emails.get(id);
            Collections.sort(temp);
            list.addAll(temp);
            results.add(list);
        }
        return results;
    }
}

class UnionFind {
    int[] father = null;
    
    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
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
//time: O(n), space: O(n)