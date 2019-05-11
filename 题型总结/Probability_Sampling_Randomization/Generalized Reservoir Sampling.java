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
