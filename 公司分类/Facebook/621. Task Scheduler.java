/* Given a char array representing tasks CPU need to do. It contains capital letters A to Z 
where different letters represent different tasks. Tasks could be done without original order. 
Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, 
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B. */

class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        Map<Character, Integer> ch2count = new HashMap<>(); 
        Queue<Integer> maxheap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        }); // #task left to do
        for (char c : tasks) {
            ch2count.putIfAbsent(c, 0);
            ch2count.put(c, ch2count.get(c) + 1);
        }
        for (char key : ch2count.keySet()) {
            maxheap.offer(ch2count.get(key));
        }
        
        int time = 0;
        Map<Integer, Integer> time2rest = new HashMap<>(); // hold the tasks
        while (!maxheap.isEmpty() || !time2rest.isEmpty()) {
            if (time2rest.containsKey(time)) {
                maxheap.offer(time2rest.get(time)); //put the rest of frequency back into the heap
                time2rest.remove(time);
            }
            if (!maxheap.isEmpty()) {
                int freq = maxheap.poll();
                if (freq > 1) {
                    time2rest.put(time + n + 1, freq - 1);
                }
            }
            time++;
        }
        return time;
    }
}

/* 算法：本质上是希望频率高的先做，所以需要用到maxheap. 最后一个while是把最高频率的依次从heap里拿出来，拿出来后算这个task下一次可以出现的时候，以及剩余多少个，然后time2rest hold这个task, 等时间到了再放出来。

时间复杂度：O(nlogn)
空间复杂度：O(n)



算法：有(count[25] - 1)个block, 每个block要有(n+1)个元素才能使间隔为n */