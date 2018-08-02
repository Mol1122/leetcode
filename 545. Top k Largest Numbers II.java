public class Solution {
    Queue<Integer> pq; 
    int size;
    public Solution(int k) {
        pq = new PriorityQueue<>();
        size = k;
    }

    
    public void add(int num) {
        if (pq.size() < size) {
            pq.offer(num);
            return;
        }
        if (num > pq.peek()) {
            pq.poll();
            pq.offer(num);
        }
    }

    public List<Integer> topk() {
        Iterator it = pq.iterator();
        List<Integer> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add((Integer)it.next());
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
}

/* 算法：quick select: add O(1), topK: O(n)
         minheap: add O(logk) topK: O(klogk)
   难点：考点不在于做对，而是在于分析k大的时候用quick select更好
         topK一定要用到iterator因为是在线算法，不能每次都poll() */