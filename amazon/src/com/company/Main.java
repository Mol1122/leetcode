package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	    Main m = new Main();
//	    System.out.println(m.top3sum(2));
//        System.out.println(m.top3sum(2));
//        System.out.println(m.top3sum(3));
//        System.out.println(m.top3sum(1));
        int[] nums = {6, 4, 3, 4, 6};
        System.out.println(m.countPath(nums));
    }

    //[6, 4, 3, 4, 6] -> 4
    //[3, 8, 3] -> 0
    public int countPath(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (int num : nums) {
            if (stack.isEmpty() || num <= stack.peek()) {
                stack.push(num);
            } else {
                int prev = stack.pop();
                int occurrence = 0;
                while (!stack.isEmpty() && stack.peek() == prev) {
                    occurrence++;
                    count += Math.pow(2, occurrence);
                    stack.pop();
                }
                stack.push(num);
            }
        }
        int occurrence = 0;
        int prev = stack.pop();
        while (!stack.isEmpty() && stack.peek() == prev) {
            occurrence++;
            count += Math.pow(2, occurrence);
            stack.pop();
        }
        return count;
    }

    //find the sum of top 3 numbers
    Queue<Integer> minheap = new PriorityQueue<>(3);
    int sum = 0;
    public int top3sum(int num) {
        if (minheap.size() < 3) {
            minheap.offer(num);
            sum += num;
        } else {
            if (minheap.peek() < num) {
                sum -= minheap.poll();
                minheap.offer(num);
                sum += num;
            }
        }
        return sum;
    }
}
