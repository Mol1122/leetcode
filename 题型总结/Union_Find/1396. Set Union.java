/* There is a list composed by sets. If two sets have the same elements, merge them. In the end, there are several sets left.

Example
Example 1:

Input :list = [[1,2,3],[3,9,7],[4,5,10]]
Output:2 .
Explanation:There are 2 sets of [1,2,3,9,7] and [4,5,10] left.
Example 2:

Input:list = [[1],[1,2,3],[4],[8,7,4,5]]
Output :2
Explanation:There are 2 sets of [1,2,3] and [4,5,7,8] left.
Notice
The number of sets n <=1000.
The number of elements for each set m <= 100.
The element must be a non negative integer and not greater than 100000. */

public class Solution {
    public int setUnion(int[][] sets) {
        if (sets == null || sets.length == 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(sets.length);
        Map<Integer, Integer> num2id = new HashMap<>();
        
        for (int i = 0; i < sets.length; i++) {
            for (int j = 0; j < sets[i].length; j++) {
                num2id.putIfAbsent(sets[i][j], i);
                uf.union(i, num2id.get(sets[i][j]));        
            }
        }
        return uf.size;
    }
}

class UnionFind {
    int[] father = null;
    int size = 0;
    
    public UnionFind(int n) {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        size = n;
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
            size--;
        }
    }
}
//time: O(n), space: O(sets.length)