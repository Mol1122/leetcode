/* Median is the middle value in an ordered integer list. If the size of the list is even, there 
is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of 
the array to the very right. You can only see the k numbers in the window. Each time the 
sliding window moves right by one position. Your job is to output the median array 
for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6]. */

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        TreeSet<Node> minHeap = new TreeSet<>(new Comparator<Node>(){
            public int compare(Node a, Node b) {
                return (int)(b.val - a.val);
            }
        });
        TreeSet<Node> maxHeap = new TreeSet<>(new Comparator<Node>(){
            public int compare(Node a, Node b) {
                return (int)(b.val - a.val);
            }
        });
        List<Double> result = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return new double[0];
        }
        
        int half = (k + 1) / 2;
        for (int i = 0; i < k - 1; i++) {
            add(minHeap, maxHeap, half, new Node(i, nums[i]));
        }
        for (int i = k - 1; i < nums.length; i++) {
            add(minHeap, maxHeap, half, new Node(i, nums[i]));
            result.add(getMedian(minHeap, maxHeap));
            remove(minHeap, maxHeap, new Node(i - k + 1, nums[i - k + 1]));
        }
        double[] arr = new double[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
    
    private double getMedian(TreeSet<Node> minheap, TreeSet<Node> maxheap) {
        if (minheap.size() == maxheap.size()) {
            return (minheap.last().val + maxheap.first().val) / 2.0;
        }
        return minheap.last().val / 1.0;
    }
    
    private void add(TreeSet<Node> minHeap, TreeSet<Node> maxHeap, int size, Node node) {
        if (minHeap.size() < size) {
            minHeap.add(node);
        } else {
            maxHeap.add(node);
        }
        
        if (maxHeap.size() != 0 && minHeap.last().val > maxHeap.first().val) {
            Node minRoot = minHeap.last();
            Node maxRoot = maxHeap.first();
            minHeap.remove(minRoot);
            maxHeap.remove(maxRoot);
            minHeap.add(maxRoot);
            maxHeap.add(minRoot);
        }
    }
    private void remove(TreeSet<Node> minHeap, TreeSet<Node> maxHeap, Node node) {
        if (minHeap.contains(node)) {
            minHeap.remove(node);
        } else {
            maxHeap.remove(node);
        }
    }
}
class Node {
    int id;
    double val;
    public Node(int id, double val) {
        this.id = id;
        this.val = val;
    }
}