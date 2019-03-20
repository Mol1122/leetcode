public class Solution {
  public long fibonacci(int n) {
  /*    if (n == 0) {
          return 0;
      } else if (n == 1) {
          return 1;
      }
      return fibonacci(n - 1) + fibonacci(n - 2);  */
    if (n <= 0) {
        return 0;
    }
    if (n == 1) {
        return 1;
    }
 
    long a = 0, b = 1, c = 0;
    for (int i = 2; i <= n; i++) {
        c = a + b;
        a = b;
        b = c;
    }
    return c;
  }
}


/* 第一种方法的时间复杂度：O(2^n) 
** 空间复杂度：O(n) */