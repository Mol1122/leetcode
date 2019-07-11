/* Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

Input: words: ["This", "is", "an", "example", "of", "text", "justification."]    L: 16.

Output:[

          "This    is    an",

            "example  of text",

            "justification.  "

           ]

    

Input: words: [“This”, “is”, “my”]       L = 5

    Output: [

         “This ”,

         “is my”    

        ]     */

public class Solution {
  public ArrayList<String> fullJustify(String[] words, int maxWidth) {
    ArrayList<String> results = new ArrayList<>();
    if (words == null || words.length == 0) {
      return results;
    }
    ArrayList<String> temp = new ArrayList<>();
    int length = 0;

    for (int i = 0; i < words.length; i++) {
      if (length + words[i].length() + temp.size() <= maxWidth) {
        temp.add(words[i]);
        length += words[i].length();
      } else {
        int space = maxWidth - length;
        int space1;

        if (temp.size() > 1) {
          space1 = space / (temp.size() - 1);
        } else {
          space1 = space;
        }
        int remaining = space - space1 * (temp.size() - 1);
        results.add(addSpace(temp, space1, remaining, maxWidth)); 
        temp.clear();
        length = 0;
        i--;
      }
    }
    if (temp.size() != 0) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < temp.size(); i++) {
        sb.append(temp.get(i) + " ");
      }
      sb.deleteCharAt(sb.length() - 1);
      for (int i = sb.length(); i < maxWidth; i++) {
        sb.append(" ");
      }
      results.add(sb.toString());
    }
    return results;
  }

  private String addSpace(ArrayList<String> temp, int space1, int remaining, int maxWidth) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < temp.size(); i++) {
      sb.append(temp.get(i));
      if (i == temp.size() - 1) {
        continue;
      }
      for (int j = 0; j < space1; j++) {
        sb.append(" ");
      }
      if (remaining > 0) {
        sb.append(" ");
        remaining--;
      }
    }
    while (sb.length() < maxWidth) {
      sb.append(" ");
    }
    return sb.toString();
  }
}
//time: O(n^2), space: O(n)