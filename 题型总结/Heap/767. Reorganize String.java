/* Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: "" */

class Solution {
    public String reorganizeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        
        Map<Character, Integer> ch2count = new HashMap<>();
        for (char c : s.toCharArray()) {
            ch2count.putIfAbsent(c, 0);
            ch2count.put(c, ch2count.get(c) + 1);
            if (ch2count.get(c) > (n + 1) / 2) {
                return "";
            }
        }
        Queue<Pair> maxheap = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return b.freq - a.freq;
            }
        });
        for (char c : ch2count.keySet()) {
            maxheap.offer(new Pair(c, ch2count.get(c)));
        }
        
        StringBuilder sb = new StringBuilder();
        while (!maxheap.isEmpty()) {
            Pair first = maxheap.poll();
            if (sb.length() == 0 || first.c != sb.charAt(sb.length() - 1)) {
                sb.append(first.c);
                first.freq--;
                if (first.freq > 0) {
                    maxheap.offer(first);
                }
            } else {
                Pair second = maxheap.poll();
                sb.append(second.c);
                second.freq--;
                if (second.freq > 0) {
                    maxheap.offer(second);
                }
                maxheap.offer(first); //易漏
            }
        }
        return sb.toString();
    }
}

class Pair {
    char c;
    int freq;
    
    public Pair(char c, int freq) {
        this.c = c;
        this.freq = freq;
    }
}
/* 算法：先按照count的大小存到map里面，然后放到heap里从大到小拿出来，最多的放第一个
** 时间复杂度：O(nlogn) */