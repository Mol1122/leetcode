public class Solution {
  static class Element {
      int value;
      List<Integer> compredValues;
    
      Element(int value) {
          this.value = value;
          this.compredValues = new ArrayList<>();
      }
  }
  public int[] largestAndSecond(int[] array) {
      Element[] helper = convert(array);
      int largerLength = array.length;
      
      while (largerLength > 1) {
          compareAndSwap(helper, largerLength);
          largerLength = (largerLength + 1) / 2;
      }
      return new int[] {helper[0].value, largest(helper[0].compredValues)};
  }
  
  private int largest(List<Integer> list) {
      int max = list.get(0);
      for (int num : list) {
          max = Math.max(max, num);
      }
      return max;
  }
  
  private void compareAndSwap(Element[] helper, int largerLength) {
      for (int i = 0; i < largerLength / 2; i++) {
          if (helper[i].value < helper[largerLength - 1 - i].value) {
              swap(helper, i, largerLength - 1 - i);
          }
          helper[i].compredValues.add(helper[largerLength - 1 - i].value);
      }
  }
  
  private void swap(Element[] helper, int left, int right) {
      Element temp = helper[left];
      helper[left] = helper[right];
      helper[right] = temp;
  }
  
  private Element[] convert(int[] array) {
      Element[] helper = new Element[array.length];
      for (int i = 0; i < array.length; i++) {
          helper[i] = new Element(array[i]);
      }
      return helper;
  }
}
