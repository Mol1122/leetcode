public class MovingAverage {
    /*
    * @param size: An integer
    */
    Queue<Integer> queue;
    int size;
    double sum;
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        sum += val;
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        queue.offer(val);
        return sum / queue.size();
    }
}

/* 算法：因为要保持先进先出的顺序，因此用queue就可以实现
** 时间复杂度：O(n) */