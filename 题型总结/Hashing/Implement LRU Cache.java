public class Solution<K, V> {
  int size, capacity;
  Map<K, ListNode<K, V>> keyToPrev;
  ListNode<K, V> dummy, tail;

  public Solution(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    keyToPrev = new HashMap<>();
    dummy = new ListNode();
    tail = dummy;
  }

  private void moveToTail(K key) {
      ListNode<K, V> prev = keyToPrev.get(key);
      ListNode<K, V> curr = prev.next;

      if (curr == tail) {
          return;
      }
      prev.next = prev.next.next;
      tail.next = curr;
      if (prev.next != null) {
          keyToPrev.put(prev.next.key, prev);
      } else {
          tail = prev;
      }
      keyToPrev.put(curr.key, tail);
      tail = tail.next;
  }
  
  public void set(K key, V value) {
    if (get(key) != null) {
        ListNode<K, V> prev = keyToPrev.get(key);
        prev.next.val = value;
        return;
    }
    if (size < capacity) {
        size++;
        ListNode<K, V> newNode = new ListNode<K, V>(key, value);
        tail.next = newNode;
        keyToPrev.put(newNode.key, tail);
        tail = tail.next;
    } else {
        ListNode first = dummy.next;
        keyToPrev.remove(first.key);

        first.key = key;
        first.val = value;
        keyToPrev.put(key, dummy);
        moveToTail(key);
    }
  }
  
  public V get(K key) {
    ListNode<K, V> node = keyToPrev.get(key);
    if (node == null) {
        return null;
    }
    V value = keyToPrev.get(key).next.val;
    moveToTail(key);
    return value;
  }
}

class ListNode<K, V> {
    K key;
    V val;
    ListNode<K, V> next;

    public ListNode(K key, V val) {
        this.key = key;
        this.val = val;
        this.next = null;
    }

    public ListNode() {

    }
}
