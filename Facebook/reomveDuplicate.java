//remove the duplicates and leave the last occurrence
    public static DoublelyNode reomveDuplicate(DoublelyNode head, DoublelyNode tail) {
        Set<Integer> set = new HashSet<>();
        DoublelyNode curr = tail;
        DoublelyNode prev = null;

//        System.out.println(head.val);
//        System.out.println(tail.val);
//        return head;
        while (curr != null) {
            if (!set.contains(curr.val)) {
                set.add(curr.val);
                prev = curr;
                curr = curr.prev;
            } else {
                prev.prev = curr.prev;
                curr.prev.next = curr.next;
                curr = curr.prev;
            }
        }
        return prev;
    }