class Solution {
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0) {
            return 0;
        }
        Arrays.sort(courses, (a, b)-> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        int time = 0;
        for (int i = 0; i < courses.length; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                pq.offer(courses[i][0]);
            } else if (!pq.isEmpty() && courses[i][0] < pq.peek()) {
                time += courses[i][0] - pq.poll();
                pq.offer(courses[i][0]);
            }
        }
        return pq.size();
    }
}

/*  算法：把courses按结束时间排序，如果能上就上，如果不能，并且开始时间更早，那么把原来的课删掉替换成新的课
**  时间复杂度：O(n) 
**  空间复杂度：O(n) */