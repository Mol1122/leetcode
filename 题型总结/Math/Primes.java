/* Return the primes less than or equals to target in ascending order.

Assumptions:

The given target is >= 2.
Examples:

target = 3,  return [2, 3]
target = 10, return [2, 3, 5, 7]
  */  
  
public class Solution {
  public List<Integer> primes(int n) {
    List<Integer> results = new ArrayList<>();
    for (int i = 2; i <= n; i++) {
      if (isPrime(i)) {
        results.add(i);
      }
    }
    return results;
  }

  private boolean isPrime(int n) {
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
//time: O(n^2), space: O(1)