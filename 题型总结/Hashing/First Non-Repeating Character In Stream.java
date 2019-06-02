/* Given a stream of characters, find the first non-repeating character from stream. You need to tell the first non-repeating 
character in O(1) time at any moment.

Implement two methods of the class:

read() - read one character from the stream
firstNonRepeating() - return the first non-repoeating character from the stream at any time when calling the method, 
return null if there does not exist such characters
Examples:

read:

a   b   c   a   c   c    b

firstNonRepeating:

a   a   a   b   b   b   null */

public class Solution {
  ListNode dummy, tail;
  Map<Character, ListNode> charToPrev;
  Set<Character> duplicates;

  public Solution() {
    dummy = new ListNode('0');
    tail = dummy;
    charToPrev = new HashMap<>();
    duplicates = new HashSet<>();
  }
  
  public void read(char ch) {
    if (duplicates.contains(ch)) {
        return;
    }
    if (charToPrev.containsKey(ch)) {
        remove(ch);
        duplicates.add(ch);
    } else {
        tail.next = new ListNode(ch);
        charToPrev.put(ch, tail);
        tail = tail.next;
    }
  }

  private void remove(char ch) {
    if (!charToPrev.containsKey(ch)) {
        return;
    }
    ListNode prev = charToPrev.get(ch);

    prev.next = prev.next.next;
    charToPrev.remove(ch);
    if (prev.next != null) {
        charToPrev.put(prev.next.c, prev);
    } else {
        tail = prev;
    }
  }
  
  public Character firstNonRepeating() {
    if (dummy.next != null) {
        return dummy.next.c;
    }
    return null;
  }
}

class ListNode {
    char c;
    ListNode next;

    public ListNode(char c) {
        this.c = c;
        this.next = null;
    }
}
