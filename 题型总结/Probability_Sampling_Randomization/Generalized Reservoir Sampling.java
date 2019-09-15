/* Consider an unlimited flow of data elements. How do you sample k element from this flow, such that at any point during 
the processing of the flow, you can return a random set of k elements from the n elements read so far. 

Assumptions

k >= 1
You will implement two methods for a sampling class:

read(int value) - read one number from the flow
sample() - return at any time the k samples as a list, return the list of all values read when the number of values read 
s o fas <= k.

You may need to add more fields for the class. */

public class Solution {
  private final int k;
  int count;
  List<Integer> nums = new ArrayList<>();
  Random rand = new Random();
  
  public Solution(int k) {
      // Complete the constructor if necessary.
      this.k = k;
      this.count = 0;
  }
  
  public void read(int value) {
      if (count < k) {
          nums.add(value);
          count++;
      } else {
          count++;
          int r = rand.nextInt(count);
          if (r < k) {
              nums.set(r, value);
          }
      }
  }
  
  public List<Integer> sample() {
    return nums;
  }
}
