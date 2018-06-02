class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        for (int i = 0; i < k - 1; i++) { //前k-1个数
            indeque(deque, nums[i]);
        }
        for (int i = k - 1; i < nums.length; i++) {
            indeque(deque, nums[i]);
            result.add(deque.peekFirst());
            outdeque(deque, nums[i - k + 1]);
        }
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
    private void indeque(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && num > deque.peekLast()) {
            deque.pollLast();
        }
        deque.offerLast(num);
    }
    private void outdeque(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }
}

/* 双端队列唯一需要掌握的题，维护一个候选可能的最大值集合 -> 从上往下单调递减 */