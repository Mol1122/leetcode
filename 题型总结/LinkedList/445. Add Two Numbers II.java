/**
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7 */

class Solution {
    //normal order
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //时间复杂度：O(n), 空间复杂度: O(1)
        String num1 = "", num2 = "";
        
        while (l1 != null) {
            num1 += l1.val;
            l1 = l1.next;
        }
        while (l2 != null) {
            num2 += l2.val;
            l2 = l2.next;
        }
        
        int carry = 0;
        String result = "";
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? num1.charAt(i) - '0' : 0;
            sum += j >= 0 ? num2.charAt(j) - '0': 0;
            
            result = (sum % 10) + result;
            carry = sum / 10;
        }
        if (carry != 0) {
            result = carry + result;
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (int i = 0; i < result.length(); i++) {
            tail.next = new ListNode(result.charAt(i) - '0');
            tail = tail.next;
        }
        return dummy.next;
    }
}

/* 算法1: 直接转化成reverse order做
      2: 如果不能reverse -> 转化成int(溢出) 或者string， 
      3. 每次用slow, fast pointer的方法找到最后一个，倒数第二个，倒数第三个这么相加，但是时间复杂度很高 */