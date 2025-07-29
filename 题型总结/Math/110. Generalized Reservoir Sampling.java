/* Consider an unlimited flow of data elements. How do you sample k element from this flow, such that at any point during the processing of the flow, you can return a random set of k elements from the n elements read so far. 

Assumptions

k >= 1
You will implement two methods for a sampling class:

read(int value) - read one number from the flow
sample() - return at any time the k samples as a list, return the list of all values read when the number of values read so fas <= k.
You may need to add more fields for the class. */


public class Solution {
  private final int k;
  int count;
  List<Integer> nums;
  Random rand;
  
  public Solution(int k) {
    this.k = k;
    count = 0;
    nums = new ArrayList<>();
    rand = new Random();
  }
  
  public void read(int value) {
    count++;
    if (count <= k) {
      nums.add(value);
    } else {
      int j = rand.nextInt(count);
      if (j < k) {
        nums.set(j, value);
      }
    }
  }
  
  public List<Integer> sample() {
    return nums;
  }
}
