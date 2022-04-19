/* Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, 
find the median of the element inside the window at each moving. (If there are even numbers in the array, 
return the N/2-th number after sorting the element in the window. )

Example
Example 1:

Input:
[1,2,7,8,5]
3
Output:
[2,7,7]

Explanation:
At first the window is at the start of the array like this `[ | 1,2,7 | ,8,5]` , return the median `2`;
then the window move one step forward.`[1, | 2,7,8 | ,5]`, return the median `7`;
then the window move one step forward again.`[1,2, | 7,8,5 | ]`, return the median `7`;
Example 2:

Input:
[1,2,3,4,5,6,7]
4
Output:
[2,3,4,5]

Explanation:
At first the window is at the start of the array like this `[ | 1,2,3,4, | 5,6,7]` , return the median `2`;
then the window move one step forward.`[1,| 2,3,4,5 | 6,7]`, return the median `3`;
then the window move one step forward again.`[1,2, | 3,4,5,6 | 7 ]`, return the median `4`;
then the window move one step forward again.`[1,2,3,| 4,5,6,7 ]`, return the median `5`; */

//preferred solution, similar to data steam one
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new double[0];
        }
        Queue<Integer> minheap = new PriorityQueue<>();
        Queue<Integer> maxheap = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        });
        List<Double> list = new ArrayList<>();

        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
                list.add(getMedian(maxheap, minheap));
                remove(maxheap, minheap, nums[i - k]);
            }
            if (i < nums.length) {
                add(maxheap, minheap, nums[i]);   
            }
        }
        double[] results = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            results[i] = list.get(i);
        } 
        return results;
    }
    
    private void add(Queue<Integer> maxheap, Queue<Integer> minheap, int num) {
        if (num <= getMedian(maxheap, minheap)) {
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
    
    private void remove(Queue<Integer> maxheap, Queue<Integer> minheap, int num) {
        if (num <= getMedian(maxheap, minheap)) {
            maxheap.remove(num);
        } else {
            minheap.remove(num);
        }
        if (minheap.size() > maxheap.size()) {
            maxheap.offer(minheap.poll());
        }
        if (maxheap.size() - minheap.size() > 1) {
            minheap.offer(maxheap.poll());
        }
    }
    
    private double getMedian(Queue<Integer> maxheap, Queue<Integer> minheap) {
        if (maxheap.isEmpty() && minheap.isEmpty()) {
            return 0;
        }

        if (maxheap.size() == minheap.size()) {
            return ((double)maxheap.peek() + (double)minheap.peek()) / 2.0;
        }
        else {
            return (double)maxheap.peek();
        }
    }
}

//other solutions
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        TreeSet<Node> maxheap = new TreeSet<>(new Comparator<Node>(){
            public int compare(Node a, Node b) {
                if (a.val == b.val) {
                    return a.id - b.id;
                }
                return a.val - b.val;
            }
        });
        TreeSet<Node> minheap = new TreeSet<>(new Comparator<Node>(){
            public int compare(Node a, Node b) {
                if (a.val == b.val) {
                    return a.id - b.id;
                }
                return a.val - b.val;
            }
        });
        int half = (k + 1) / 2;
        for (int i = 0; i < k - 1; i++) {
            add(maxheap, minheap, half, new Node(i, nums[i]));
        }
        for (int i = k - 1; i < nums.length; i++) {
            add(maxheap, minheap, half, new Node(i, nums[i]));
            results.add(maxheap.last().val);
            remove(maxheap, minheap, new Node(i - k + 1, nums[i - k + 1]));
        }
        return results;
    }

    private void remove(TreeSet<Node> maxheap, TreeSet<Node> minheap, Node node) {
        if (maxheap.contains(node)) {
            maxheap.remove(node);
        } else {
            minheap.remove(node);
        }
    }

    private void add(TreeSet<Node> maxheap, TreeSet<Node> minheap, int half, Node node) {
        if (maxheap.size() < half) {
            maxheap.add(node);
        } else {
            minheap.add(node);
        }
        if (minheap.size() != 0 && maxheap.last().val > minheap.first().val) {
            Node max = maxheap.last();
            Node min = minheap.first();
            maxheap.remove(max);
            minheap.remove(min);
            maxheap.add(min);
            minheap.add(max);
        }
    }
}

class Node {
    int id, val;

    public Node(int id, int val) {
        this.id = id;
        this.val = val;
    }
}

public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        TreeSet<Node> maxheap = new TreeSet<>();
        TreeSet<Node> minheap = new TreeSet<>();
        
        int half = (k + 1) / 2;
        for (int i = 0; i < k - 1; i++) {
            add(maxheap, minheap, half, new Node(i, nums[i]));
        }
        for (int i = k - 1; i < nums.length; i++) {
            add(maxheap, minheap, half, new Node(i, nums[i]));
            results.add(minheap.last().val);
            remove(maxheap, minheap, new Node(i - k + 1, nums[i - k + 1]));
        }
        return results;
    }
    
    private void add(TreeSet<Node> maxheap, TreeSet<Node> minheap, int size,
                     Node node) {
        if (minheap.size() < size) {
            minheap.add(node);
        } else {
            maxheap.add(node);
        }
        
        if (maxheap.size() != 0 && minheap.last().val > maxheap.first().val) {
            Node maxNode = minheap.last();
            Node minNode = maxheap.first();
            minheap.remove(maxNode);
            maxheap.remove(minNode);
            maxheap.add(maxNode);
            minheap.add(minNode);
        }
    }
    
    private void remove(TreeSet<Node> maxheap, TreeSet<Node> minheap, Node node) {
        if (maxheap.contains(node)) {
            maxheap.remove(node);
        } else {
            minheap.remove(node);
        }
    }
}

class Node implements Comparable<Node> {
    int id, val;
    public Node (int id, int val) {
        this.id = id;
        this.val = val;
    }
    
    public int compareTo(Node o) {
        Node node = (Node)o;
        if (this.val == node.val) {
            return this.id - node.id;
        }
        return this.val - node.val;
    }
}

/* 时间复杂度：O(nlogn)
** 难点：TreeSet是一个balanced BST, 
         这题跟data data stream相同的是，这题让maxheap的个数比minheap多1 */