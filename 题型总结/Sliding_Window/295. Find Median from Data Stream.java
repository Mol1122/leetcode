/* The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0 */

class MedianFinder {
    Queue<Integer> minheap;
    Queue<Integer> maxheap;

    public MedianFinder() {
        minheap = new PriorityQueue<>();
        maxheap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
    }
    
    public void addNum(int num) {
        if (num <= findMedian()) {
            maxheap.offer(num);
        } else {
            minheap.offer(num);
        }
        if (minheap.size() > maxheap.size()) {
            maxheap.offer(minheap.poll());
        }
        if (maxheap.size() - minheap.size() > 1) {
            minheap.offer(maxheap.poll());
        }
    }
    
    public double findMedian() {
        if (maxheap.isEmpty() && minheap.isEmpty()) {
            return 0.0;
        }
        if (maxheap.size() == minheap.size()) {
            return ((double)maxheap.peek() + (double)minheap.peek()) / 2.0;
        }
        return (double)maxheap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

class MedianFinder {
    Queue<Integer> minheap;
    Queue<Integer> maxheap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minheap = new PriorityQueue<>();
        maxheap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
    }
    
    public void addNum(int num) {
        if (maxheap.size() < minheap.size() + 1) {
            if (minheap.size() == 0) {
                maxheap.offer(num);
            } else {
                maxheap.offer(num);
                if (maxheap.peek() > minheap.peek()) {
                    int max = maxheap.poll();
                    int min = minheap.poll();
                    maxheap.offer(min);
                    minheap.offer(max);
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
    
    public double findMedian() {
        if ((maxheap.size() + minheap.size()) % 2 == 0) {
            return (maxheap.peek() + minheap.peek()) / 2.0;
        }
        return maxheap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */