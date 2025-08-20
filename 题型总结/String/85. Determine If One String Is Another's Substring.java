/* Determine if a small string is a substring of another large string.

Return the index of the first occurrence of the small string in the large string.

Return -1 if the small string is not a substring of the large string.

Assumptions

Both large and small are not null
If small is empty string, return 0
Examples

“ab” is a substring of “bcabc”, return 2
“bcd” is not a substring of “bcabc”, return -1
"" is substring of "abc", return 0 */

public class Solution {
  public int strstr(String large, String small) {
      if (large.length() < small.length()) {
          return -1;
      }
      if (small.length() == 0) {
          return 0;
      }
      int largePrime = 101;
      int prime = 31;
      int targetHash = small.charAt(0) % largePrime;
      int seed = 1;
    
      for (int i = 1; i < small.length(); i++) {
          seed = moduleHash(seed, 0, prime, largePrime);
          targetHash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
      }
      int hash = 0;
      for (int i = 0; i < small.length(); i++) {
          hash = moduleHash(hash, large.charAt(i), prime, largePrime);
      }
      if (hash == targetHash && equal(large, 0, small)) {
          return 0;
      }
      for (int i = 0; i < large.length() - small.length(); i++) {
          hash = noNegative(hash - seed * large.charAt(i) % largePrime, largePrime);
          hash = moduleHash(hash, large.charAt(i + small.length()), prime, largePrime);
          if (hash == targetHash && equal(large, i + 1, small)) {
              return i + 1;
          }
      }
      return -1;
  }
  
  private int noNegative(int hash, int largePrime) {
      if (hash < 0) {
          return hash + largePrime;
      }
      return hash;
  }
  
  private boolean equal(String large, int start, String small) {
      for (int i = start; i < start + small.length(); i++) {
          if (large.charAt(i) != small.charAt(i - start)) {
              return false;
          }
      }
      return true;
  }
  
  private int moduleHash(int hash, int addition, int prime, int largePrime) {
      return (hash * prime % largePrime + addition) % largePrime;
  }
}
/* 算法：Rabin Karp
** 时间复杂度：O(n + m) in average
** 空间复杂度：O(1) */