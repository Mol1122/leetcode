public class DataStream {
	class Node {
		int val;
		long start_time;

		public Node(int val, long time) {
			this.val = val;
			this.start_time = time;
		}
	}
	long sum;
	Queue<Node> minheap = new PriorityQueue<>(new Comparator<Node>() {
		public int compare(Node a, Node b) {
			return a.start_time - b.start_time;
		}
	});

	public void record(int x) {
		long currTime = getNode();
		Node node = new Node(x, currTime);
		minheap.offer(node);
		sum += x;
	}

	public double getMean() {
		removeExpire();
		if (!minheap.isEmpty()) {
			return sum * 1.0 / minheap.size();
		}
		return 0.0;
	}

	public void removeExpire() {
		while (!minheap.isEmpty()) {
			Node node = minheap.peek();
			if (getNow() - node.start_time > 300) {
				sum -= minheap.pop().val;
			}
		}
	}
}