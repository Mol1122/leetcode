/* Given a string S, check if the letters can be rearranged so that two characters 
that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: "" */

class Solution {
    public String reorganizeString(String S) {
        // Create map of each char to its count
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            // Impossible to form a solution
            if (count > (S.length() + 1) / 2) {
                return "";    
            }
            map.put(c, count);
        }
        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        Queue<int[]> maxheap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });
        for (char c : map.keySet()) {
            maxheap.add(new int[] {c, map.get(c)});
        }
        // Build the result.
        StringBuilder sb = new StringBuilder();
        while (!maxheap.isEmpty()) {
            int[] first = maxheap.poll();
            if (sb.length() == 0 || (char)first[0] != sb.charAt(sb.length() - 1)) { //可以加first的字符
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    maxheap.add(first);
                }
            } else {
                int[] second = maxheap.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    maxheap.add(second);
                }
                maxheap.add(first);
            }
        }
        return sb.toString();
    }
}

/* 算法：先按照count的大小存到map里面，然后放到heap里从大到小拿出来，最多的放第一个
** 时间复杂度：O(nlogn) */