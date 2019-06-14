/* Given a string, we can insert at most one empty space between each pair of adjacent letters. 

Print all permutations of strings after insertions of empty spaces. 

Input: str = "ABC" 

Output: 

ABC

AB_C


A_BC


A_B_C */

class Solution {
  public List<String> allPermutation(String s) {
    List<String> results = new ArrayList<>();
    if (s == null || s.length() == 0) {
      return results;
    }
    if (s.length() == 1) {
      results.add(s);
      return results;
    }
    dfs(s, 0, new StringBuilder(), results);
    return results;
  }
  
  private void dfs(String s, int index, StringBuilder sb, List<String> results) {
    if (index == s.length() - 1) {
      sb.append(s.charAt(s.length() - 1));
      results.add(new String(sb));
      sb.deleteCharAt(sb.length() - 1); //important
      return;
    }
    //add char and space
    sb.append(s.charAt(index));
    sb.append(" ");
    dfs(s, index + 1, sb, results);
    sb.deleteCharAt(sb.length() - 1);
    sb.deleteCharAt(sb.length() - 1);
    
    sb.append(s.charAt(index));
    dfs(s, index + 1, sb, results);
    sb.deleteCharAt(sb.length() - 1);
  }
}
//time: O(2^n), space: O(n)