/**
Given a string containing only 'A', 'B', 'C', 'D', return the number of occurrences of substring which has 
length 4 and includes all of the characters from 'A', 'B', 'C', 'D' with in descending sorted order.

Assumptions:

The given string is not null and has length of >= 4.
In the output, if two substrings have the same frequency, then they should be sorted by the their natural order.
Examples:

 “ABCDABCDD”, --> {"ABCD" : 2, "BCDA" : 1, "CDAB" : 1, "DABC" : 1} */

public class Solution {
  public List<Frequency> frequency(String s) {
    List<Frequency> results = new ArrayList<>();
    if (s == null || s.length() == 0) {
        return results;
    }
    Map<String, Integer> str2count = new HashMap<>();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
        sb.append(s.charAt(i));
        if (i >= 4) {
            sb.deleteCharAt(0);
        }
        if (isValid(sb)) {
            str2count.putIfAbsent(sb.toString(), 0);
            str2count.put(sb.toString(), str2count.get(sb.toString()) + 1);
        }
    }
    for (String key : str2count.keySet()) {
        results.add(new Frequency(key, str2count.get(key)));
    }
    Collections.sort(results, new Comparator<Frequency>(){
        public int compare(Frequency a, Frequency b) {
            if (a.freq == b.freq) {
                return a.str.compareTo(b.str);
            }
            return b.freq - a.freq;
        }
    });
    return results;
  }

  private boolean isValid(StringBuilder sb) {
    int[] cnt = new int[4];
    for (int i = 0; i < sb.length(); i++) {
        cnt[sb.charAt(i) - 'A']++;
    }
    for (int i = 0; i < 4; i++) {
        if (cnt[i] != 1) {
            return false;
        }   
    }
    return true;
  }
}
//time: O(n^2) 因为有sb.deleteCharAt(0), 如果忽略不记就是O(nlogn) space: O(n)