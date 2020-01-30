/* Give a number of arrays, find their intersection, and output their intersection size.

Example
Example 1:

    Input:  [[1,2,3],[3,4,5],[3,9,10]]
    Output:  1
    
    Explanation:
    Only '3' in all three array.
Example 2:

    Input: [[1,2,3,4],[1,2,5,6,7][9,10,1,5,2,3]]
    Output:  2
    
    Explanation:
    The set is [1,2].
Notice
The total number of all array elements is not more than 500000.
There are no duplicated elements in each array. */

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
        Queue<Pair> minheap = new PriorityQueue<>(k, new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return arrs[a.x][a.y] - arrs[b.x][b.y];
            }
        });
        for (int i = 0; i < k; i++) {
            if (arrs[i].length > 0) {
                minheap.offer(new Pair(i, 0));
            }
        }
        int preValue = -1, count = 0;
        int intersections = 0;
        
        while (!minheap.isEmpty()) {
            Pair p = minheap.poll();
            if (arrs[p.x][p.y] != preValue || count == 0) {
                if (count == k) {
                    intersections++;
                }
                preValue = arrs[p.x][p.y];
                count = 1;
            } else {
                count++;
            }
            p.y++;
            if (p.y < arrs[p.x].length) {
                minheap.offer(p);
            }
        }
        if (count == k) {
            intersections++;
        }
        return intersections;
    }
}

class Pair {
    int x, y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
/* 算法：使用priorityQueue, 空间复杂度更好
时间复杂度为 O(knlogn + nklogk)
其中 knlogn 是 k 个数组进行分别排序的时间复杂度
nklogk 是 总共 nk 个数从 PriorityQueue 中进出，每次进出 logk */