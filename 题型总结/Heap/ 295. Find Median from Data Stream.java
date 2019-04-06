class MedianFinder {
    Queue<Integer> maxheap;
    Queue<Integer> minheap;
    
    public MedianFinder() {
        maxheap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        minheap = new PriorityQueue<>();
    }
    
    //time: O(nlogn), space: O(n)
    public void addNum(int num) {
        if (maxheap.size() < minheap.size() + 1) {
            if (minheap.isEmpty()) {
                maxheap.offer(num);
            } else {
                maxheap.offer(num);
                if (maxheap.peek() > minheap.peek()) {
                    int max = maxheap.poll();
                    int min = minheap.poll();
                    minheap.offer(max);
                    maxheap.offer(min);
                }
            }
        } else {
            minheap.offer(num);
            if (maxheap.peek() > minheap.peek()) {
                int max = maxheap.poll();
                int min = minheap.poll();
                maxheap.offer(min);
                minheap.offer(max);
            }
        }
    }
    
    //time: O(1), space: O(1)
    public double findMedian() {
        if ((maxheap.size() + minheap.size()) % 2 == 0) {
            return (maxheap.peek() + minheap.peek()) / 2.0;
        }
        return maxheap.peek() / 1.0;
    }
}
