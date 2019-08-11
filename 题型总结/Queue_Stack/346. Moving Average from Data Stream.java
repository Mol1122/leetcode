/* Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3 */

class MovingAverage {
    Queue<Integer> queue = new LinkedList<>();
    int sum = 0;
    int size = 0;
    
    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        sum += val;
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        queue.offer(val);
        return (double)sum / queue.size();
    }
}
//算法：用queue去表示sliding window
//time: O(n), space: O(size)