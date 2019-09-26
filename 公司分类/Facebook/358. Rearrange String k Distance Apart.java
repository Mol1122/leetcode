/* Given a non-empty string s and an integer k, rearrange the string such that the same 
characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the 
string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc" 
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: "" 
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other. */

class Solution {
    public String rearrangeString(String s, int k) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (k == 0) {
            return s;
        }
        Map<Character, Integer> ch2count = new HashMap<>();
        for (char c : s.toCharArray()) {
            ch2count.putIfAbsent(c, 0);
            ch2count.put(c, ch2count.get(c) + 1);
        }
        Queue<Character> maxheap = new PriorityQueue<>(new Comparator<Character>(){
            public int compare(Character a, Character b) {
                return ch2count.get(b) - ch2count.get(a);
            }
        });
        for (char c : ch2count.keySet()) {
            maxheap.offer(c);
        }
        Map<Integer, Character> time2ch = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        int time = 0;
        while (!maxheap.isEmpty() || !time2ch.isEmpty()) {
            if (time2ch.containsKey(time)) {
                char c = time2ch.get(time);
                time2ch.remove(time);
                maxheap.offer(c);
            } 
            if (!maxheap.isEmpty()) {
                char c = maxheap.poll();
                sb.append(c);
                ch2count.put(c, ch2count.get(c) - 1);
                if (ch2count.get(c) > 0) {
                    time2ch.put(time + k, c);
                }
            }
            time++;
            if (time > s.length()) {
                return "";
            }
        }
        if (time != s.length()) {
            return "";
        }
        return sb.toString();
    }
}
//time: O(nlogn), space: O(n)