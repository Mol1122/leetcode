/* A group of friends went on holiday and sometimes lent each other money. For example, Alice 
paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each 
transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, 
and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions 
can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions 
required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled. */

class Solution {
    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0) {
            return 0;
        }
        Map<Integer, Integer> debts = new HashMap<>();
        for (int[] t : transactions) {
            debts.put(t[0], debts.getOrDefault(t[0], 0) - t[2]);
            debts.put(t[1], debts.getOrDefault(t[1], 0) + t[2]);
        }
        List<Integer> list = new ArrayList<>(debts.values());
        Collections.sort(list);
        
        int numOfTrans = 0;
        for (int i = 2; i <= list.size(); i++) { //假设有2个人，3个人...
            while (evenUp(list, 0, i)) {
                numOfTrans += (i - 1);
            }
        }
        return numOfTrans;
    }
    
    private boolean evenUp(List<Integer> balance, int sum, int num) {
        if (num == 0) {
            return sum == 0;
        }
        for (int i = 0; i < balance.size(); i++) {
            if (balance.get(i) != 0) {
                int ori = balance.get(i);
                balance.set(i, 0);
                if (evenUp(balance, sum - ori, num - 1)) {
                    return true;
                }
                balance.set(i, ori);
            }
        }
        return false;
    }
}
/* 给一有向图,每一条边用一个 三元组 表示, 比如 [u, v, w] 代表权值为 w 的从 u 到 v 的一条边. 
计算出保证每个点的权重相等需要添加的最少的边数. 也就是说, 指向这一点的边权重总和等于这个点指向其他点的边权重之和. */
