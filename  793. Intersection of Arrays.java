public class Solution {
    /**
     * @param arrs: the arrays
     * @return: the number of the intersection of the arrays
     */
    public int intersectionOfArrays(int[][] arrs) {
        if (arrs == null || arrs.length == 0) {
            return 0;
        }
        int k = arrs.length;
        Queue<Pair> minheap = new PriorityQueue<>(k, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return arrs[p1.row][p1.col] - arrs[p2.row][p2.col];
            }
        });
        for (int i = 0; i < k; i++) {
            if (arrs[i].length == 0) {
                return 0;
            }
            Arrays.sort(arrs[i]);
            minheap.offer(new Pair(i, 0));
        }
        int last = 0, count = 0;
        int result = 0;
        while (!minheap.isEmpty()) {
            Pair p = minheap.poll();
            if (arrs[p.row][p.col] != last || count == 0) {
                if (count == k) {
                    result++;
                }
                last = arrs[p.row][p.col];
                count = 1;
            } else {
                count++;
            }
            p.col++;
            if (p.col < arrs[p.row].length) {
                minheap.offer(p); //难点， 不需要重新new 一个Pair
            }
        }
        if (count == k) { //易漏
            result++;
        }
        return result;
    }
}

class Pair {
    int row, col;
    
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

/* 算法：使用priorityQueue
时间复杂度为 O(knlogn + nklogk)
其中 knlognknlogn 是 k 个数组进行分别排序的时间复杂度
nklogknklogk 是 总共 nk 个数从 PriorityQueue 中进出，每次进出 logk */