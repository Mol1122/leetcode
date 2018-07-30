public class CircularQueue {
    int[] circularArray;
    int front;
    int rear;
    int size;
    public CircularQueue(int n) {
        circularArray = new int[n];
        front = 0;
        rear = 0;
        size = 0;
    }
    /**
     * @return:  return true if the array is full
     */
    public boolean isFull() {
        return size == circularArray.length;
    }

    /**
     * @return: return true if there is no element in the array
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element: the element given to be added
     * @return: nothing
     */
    public void enqueue(int element) {
        if (isFull()) {
            throw new RuntimeException("Queue is already full");
        }
        rear = (front + size) % circularArray.length;
        circularArray[rear] = element;
        size++;
    }

    /**
     * @return: pop an element from the queue
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int ele = circularArray[front];
        front = (front + 1) % circularArray.length;
        size--;
        return ele;
    }
    
}