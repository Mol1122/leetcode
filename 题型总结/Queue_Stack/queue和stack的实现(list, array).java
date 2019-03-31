//用linked list实现stack
class ListNode {
	int val;
	ListNode next;

	public ListNode(int val) {
		this.val = val;
		next = null;
	}
}

public Stack {
	private ListNode head;
	public Stack() {
		head = null;
	}

	public Integer pop() {
		if (head == null) {
			return null;
		}
		ListNode prev = head;
		head = head.next;
		prev.next = null;
		return prev.value;
	}

	public Integer peek() {
		if (head == null) {
			return null;
		}
		return head.value;
	}

	public void push(int x) {
		ListNode newNode = new ListNode(x);
		newNode.next = head;
		head = newNode;
	}
}

//用linked list实现queue
public class Queue {
	ListNode head;
	ListNode tail;

	public Queue() {
		head = tail = null;
	}

	public Integer poll() {
		if (head == null) {
			return null;
		}
		ListNode node = head;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		node.next = null;
		return node.value;
	}

	public Integer peek() {
		if (head == null) {
			return null;
		}
		return head.value;
	}

	public void offer(Integer x) {
		if (head == null) {
			head = new ListNode(x);
			tail = head;
		} else {
			tail.next = new ListNode(x);
			tail = tail.next;
		}
	}
}

//用bounded array实现queue
public class BoundedQueue {
	int head, tail, size;
	Integer[] array;

	public BoundedQueue(int cap) {
		array = new Integer[cap];
		head = tail = 0;
		size = 0;
	}

	public boolean offer(Integer x) {
		if (size == array.length) {
			return false;
		}
		array[tail] = x;
		tail = (tail + 1) % array.length;
		size++;
		return true;
	}

	public Integer peek() {
		if (size == 0) {
			return null;
		}
		return array[head];
	}

	public Integer poll() {
		if (size == 0) {
			return null;
		}
		Integer result = array[head];
		head =(head + 1) % array.length;
		size--;
		return result;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == array.length;
	}
}

//用bounded array实现stack
public class BoundedStack {
	int[] array;
	int head;

	public BoundedStack(int cap) {
		array = new int[cap];
		head = -1;
	}

	public boolean push(int x) {
		if (head == array.length - 1) {
			return false;
		}
		array[++head] = x;
		return true;
	}

	public Integer pop() {
		return head == -1 ? null : array[head--];
	}

	public Integer top() {
		return head == -1 ? null : array[head];
	}
}