class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (seqs == null || seqs.size() == 0) {
            return false;
        }
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        
        for (int i : org) {
            edges.put(i, new HashSet<>());
            indegree.put(i, 0);
        }
        int n = org.length;
        int count = 0;
        //calculate the indegree
        for (List<Integer> seq : seqs) {
            count += seq.size();
            if (seq.size() >= 1 && (seq.get(0) <= 0 || seq.get(0) > n)) {
                return false;
            }
            for (int i = 1; i < seq.size(); i++) {
                if (seq.get(i) <= 0 || seq.get(i) > n) {
                    return false;
                }
                if (edges.get(seq.get(i - 1)).add(seq.get(i))) { //难点：只创建从前一个数到后一个数的edge， 1->2->3，1是2的前提，那么1肯定是3的前提了，这么做避免了重复
                    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
                }
            }
        }

        if (count < n) {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i : indegree.keySet()) {
            if (indegree.get(i) == 0) {
                queue.offer(i);
            }
        }
        int cnt = 0;
        while (queue.size() == 1) { //要保证有且仅有一个答案，那么queue里面只能同时存在一个element
            int curr = queue.poll();
            for (int edge : edges.get(curr)) {
                indegree.put(edge, indegree.get(edge) - 1);
                if (indegree.get(edge) == 0) {
                    queue.offer(edge);
                }
            }
            if (curr != org[cnt]) { //易漏
                return false;
            }
            cnt++;
        }
        return cnt == org.length;
    }
}

/* 算法：拓扑排序
** 难点：edge case有点多
** 时间复杂度：O(n + m) //n = # of nodes, m = # of edges */