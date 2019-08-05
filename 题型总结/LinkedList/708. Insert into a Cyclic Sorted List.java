/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
}; */

/* Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node. */

class Solution {
    public Node insert(Node head, int val) {
        if (head == null) {
            Node newNode = new Node(val);
            newNode.next = newNode;
            return newNode;
        }
        Node curr = head;
        Node prev = null;
        
        do {
            prev = curr;
            curr = curr.next;
            if (val >= prev.val && val <= curr.val) { //如果已经找到位置
                break;
            }
            if ((prev.val > curr.val) && (val > prev.val || val < curr.val)) { //是第一个或者是最后一个
                break;
            }
        } while (curr != head);
        
        Node newNode = new Node(val);
        newNode.next = curr;
        prev.next = newNode;
        return head;
    }
}
//time: O(n), space: O(1)