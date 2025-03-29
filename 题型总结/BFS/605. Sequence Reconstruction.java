/* Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example
Example 1:

Input:org = [1,2,3], seqs = [[1,2],[1,3]]
Output: false
Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
Example 2:

Input: org = [1,2,3], seqs = [[1,2]]
Output: false
Explanation:
The reconstructed sequence can only be [1,2].
Example 3:

Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
Output: true
Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:

Input:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
Output:true */

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

        // case: nums = [1,2,3], sequences = [[1,2]]
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

class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        if (sequences == null || sequences.size() == 0) {
            return false;
        }
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        int n = nums.length;
        int count = 0;

        for (int num : nums) {
            edges.put(num, new HashSet<>());
            indegree.put(num, 0);
        }
        for (List<Integer> sequence : sequences) {
            count += sequence.size();
            if (sequence.size() > 1 && (sequence.get(0) < 0 || sequence.get(0) > n)) {
                return false;
            }
            for (int i = 1; i < sequence.size(); i++) {
                if (sequence.get(i) < 0 || sequence.get(i) > n) {
                    return false;
                }
                if (edges.get(sequence.get(i - 1)).add(sequence.get(i))) {
                    indegree.put(sequence.get(i), indegree.get(sequence.get(i)) + 1);
                }
            }
        }
        if (count < n) {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size != 1) {
                return false;
            }
            int node = queue.poll();
            if (node != nums[index++]) {
                return false;
            }
            for (int neighbor : edges.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return index == n;
    }
}