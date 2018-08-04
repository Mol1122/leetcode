public class Solution {
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    private Comparator<Pair> pairComparator = new Comparator<Pair>(){
        public int compare(Pair p1, Pair p2) {
            if (p1.freq == p2.freq) {
                return p2.word.compareTo(p1.word);
            }
            return p1.freq - p2.freq;
        }
    };
    public String[] topKFrequentWords(String[] words, int k) {
        if (words == null || words.length == 0 || k <= 0) {
            return new String[0];
        }
        Map<String, Integer> wordToFreq = new HashMap<>();
        for (String word : words) {
            if (wordToFreq.containsKey(word)) {
                wordToFreq.put(word, wordToFreq.get(word) + 1);
            } else {
                wordToFreq.put(word, 1);
            }
        }
        Queue<Pair> minheap = new PriorityQueue<>(k, pairComparator);
        
        for (String word : wordToFreq.keySet()) {
            Pair newPair = new Pair(word, wordToFreq.get(word));
            Pair top = minheap.peek();
            minheap.offer(newPair);
            if (minheap.size() > k) {
               minheap.poll(); 
            }
        }
        k = minheap.size();
        String[] results = new String[k];
        int index = 0;
        while (!minheap.isEmpty()) {
            results[index++] = minheap.poll().word;
        }
        int left = 0, right = results.length - 1;
        while (left < right) {
            String temp = results[left];
            results[left] = results[right];
            results[right] = temp;
            left++;
            right--;
        }
        return results;
    }
}

class Pair {
    String word;
    int freq;
    
    public Pair(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }
}

/* 算法：根据时间复杂度的提示就可以想到用heap. 因为是求最大，所以用minheap,而且因为最后返回的string是要按升序，
**       所以在minheap里面要反过来才能保证打擂台的时候是按升序 */