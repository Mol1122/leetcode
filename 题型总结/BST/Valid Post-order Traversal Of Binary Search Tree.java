public class Solution {
  public boolean validPostOrder(int[] post) {
      if (post == null || post.length == 0) {
          return true;
      }
      return helper(post, 0, post.length - 1);
  }
  
  private boolean helper(int[] post, int start, int end) {
      if (start >= end) {
          return true;
      }
      int x = post[end], index = end - 1;
      //find the right tree
      while (index >= start && post[index] > x) {
          index--;
      }
      int temp = index;
      //find the left tree
      while (index >= start) {
          if (post[index] > x) {
              return false;
          }
          index--;
      }
      //if all is right tree
      if (temp == start) {
          return helper(post, start, end - 1);
      } else {
          return helper(post, start, temp) && helper(post, temp + 1, end - 1);
      }
  }
}

/* 难点：30行， temp 已经是小于root的了，所以第一个是start, temp; 大于root的在temp+1和end-1之间
** 时间复杂度：O(n^2)
** 空间复杂度：O(h) */