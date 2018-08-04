class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> numToFreq = new HashMap<>();
        for (int num : nums) {
            if (numToFreq.containsKey(num)) {
                numToFreq.put(num, numToFreq.get(num) + 1);
            } else {
                numToFreq.put(num, 1);
            }
        }
        Queue<Pair> minheap = new PriorityQueue<>(k, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                if (a.freq != b.freq) {
                    return a.freq - b.freq;
                }
                return b.value - a.value;
            }
        });
        for (int num : numToFreq.keySet()) {
            Pair newPair = new Pair(num, numToFreq.get(num));
            Pair top = minheap.peek();
            minheap.offer(newPair);
            if (minheap.size() > k) {
                minheap.poll();
            }
        }
        List<Integer> results = new ArrayList<>();
        while (!minheap.isEmpty()) {
            results.add(minheap.poll().value);
        }
        
        int left = 0, right = results.size() - 1;
        while (left < right) {
            int temp = results.get(left);
            results.set(left, results.get(right));
            results.set(right, temp);
            left++;
            right--;
        }
        return results;
    }
}

class Pair {
    int value, freq;
    
    public Pair(int value, int freq) {
        this.value = value;
        this.freq = freq;
    }
}