/* Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]

Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"] */

public class Solution {
  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> results = new ArrayList<>();
    if (words == null || words.length == 0) {
      return results;
    }
    Map<String, Integer> str2index = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      str2index.put(words[i], i);
    }
    if (str2index.containsKey("")) {
      for (int i = 0; i < words.length; i++) {
        if (isPalindrome(words[i])) {
          if (str2index.get("") == i) {
            continue;
          }
          results.add(Arrays.asList(i, str2index.get("")));
          results.add(Arrays.asList(str2index.get(""), i));
        }
      }
    }

    for (int i = 0; i < words.length; i++) {
      String reverseStr = getReverse(words[i]);
      if (str2index.containsKey(reverseStr)) {
        if (str2index.get(reverseStr) == i) {
          continue;
        }
        results.add(Arrays.asList(i, str2index.get(reverseStr)));
      }
    }

    for (int i = 0; i < words.length; i++) {
      for (int j = 1; j < words[i].length(); j++) {
        String sub = words[i].substring(0, j);
        String rest = words[i].substring(j);
        if (isPalindrome(sub)) {
          String reverseRest = getReverse(rest);
          if (str2index.containsKey(reverseRest)) {
            if (str2index.get(reverseRest) == i) {
              continue;
            }
            results.add(Arrays.asList(str2index.get(reverseRest), i));
          }
        }

        if (isPalindrome(rest)) {
          String reverseSub = getReverse(sub);
          if (str2index.containsKey(reverseSub)) {
            if(str2index.get(reverseSub) == i) {
              continue;
            }
            results.add(Arrays.asList(i, str2index.get(reverseSub)));
          }
        }
      }
    }
    return results;
  }

  private String getReverse(String s) {
    StringBuilder sb = new StringBuilder(s);
    sb.reverse();
    return sb.toString();
  }

  private boolean isPalindrome(String s) {
    int start = 0, end = s.length() - 1;
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }
}
//time: O(n*wordLen^2), space: O(n)