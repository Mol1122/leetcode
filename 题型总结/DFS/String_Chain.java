/* Given an array of strings, find if all the strings can be chained to form a circle. 
Two string s1 and s2 can be chained, iff the last letter of s1 is identical to the first letter of s2.

 For example, 

“abc” and “cd” can be chained, 

“abc” and “dz” can not be chained.

 Example Input: 

arr[] = {"aaa", "bbb", "baa", "aab"}; 

Output: True, 

The given input strings can be chained to form a circle. The strings can be chained as "aaa", "aab", "bbb" and "baa" */

//NOT RECOMMENDED
public class Solution {
  public boolean hasCircle(String[] strs) {
    if (strs == null || strs.length == 0) {
      return false;
    }
    boolean[] isFound = {false};
    //assume same word cannot be used twice
    dfs(strs, 0, isFound);
    return isFound[0];
  }
  
  private void dfs(String[] strs, int index, boolean[] isFound) {
    if (index == strs.length) {
      isFound[0] = true;
      return;
    }
    for (int i = index; i < strs.length; i++) {
      if (index == 0 || strs[i].charAt(0) == strs[index - 1].charAt(strs[index].length() - 1)) {
        swap(strs, index, i);
        dfs(strs, index + 1, isF);
        swap(strs, index, i);
      }
    }
  }
  
  private void swap(String[] strs, int i, int j) {
    String temp = strs[i];
    strs[i] = strs[j];
    strs[j] = temp;
  }
}
//time: O(n!), space: O(n)

public class Solution {
  public boolean canChain(String[] strArray) {
    if (strArray == null || strArray.length == 0) {
      return false;
    }
    return dfs(strArray, 1);
  }

  private boolean dfs(String[] strAray, int index) {
    if (index == strArray.length) {
      return canConnect(strArray[index - 1], strArray[0]);
    }
    for (int i = index; i < strArray.length; i++) {
      if (canConnect(strArray[index - 1], strArray[i])) {
        swap(strAray, index, i);
        if (dfs(strArray, index + 1)) {
          return true;
        }
        swap(strArray, index, i);
      }
    }
    return false;
  }

  private boolean canConnect(String s1, String s2) {
    return s1.charAt(s1.length() - 1) == s2.charAt(0);
  }
}
//time: O(n!), space: O(n), has pruning