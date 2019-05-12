public class Solution {
    int count;
    Integer num;
    Random rand;
  
  public Solution() {
      count = 0;
      num = null;
      rand = new Random();
  }
  
  public void read(int value) {
      count++;
      if (rand.nextInt(count) == 0) {
          num = value;
      }
  }
  
  public Integer sample() {
      return num;
  }
}
