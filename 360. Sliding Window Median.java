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
** 难点：TreeSet是一个minheap 
         这题跟data frame不同的是，这题让minheap的个数比maxheap多1 */