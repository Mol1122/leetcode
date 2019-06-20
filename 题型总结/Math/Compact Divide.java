/* Given two integers a and b, return the result of a / b in String with compact format. The repeated decimal part should be identified and enclosed by "()".

Examples

0 / 2 = "0"

4 / 2 = "2"

1 / 2 = "0.5"

-14 / 12 = "-1.1(6)"

1 / 11 = "0.(09)"

1 / 0 = "NaN"

-1 / 0 = "NaN" */

public class Solution {
  public String divide(int numerator, int denominator) {
    if (denominator == 0) {
      return "NaN";
    }
    long a = numerator, b = denominator, m = a % b;
    Map<Long, Integer> map = new HashMap<>();
    if (m == 0) {
      return a / b + "";
    }
    StringBuilder sb = new StringBuilder();
    if (a * b < 0) {
      sb.append("-");
    }
    a = Math.abs(a);
    b = Math.abs(b);
    sb.append(a / b);
    sb.append(".");
    m = a % b;

    map.put(m, sb.length());
    while (m != 0) {
      a = m * 10;
      sb.append(a / b);
      m = a % b;
      if (map.containsKey(m)) {
        return sb.insert((int)map.get(m), "(").append(")").toString();
      }
      map.put(m, sb.length());
    }
    return sb.toString();
  }
}
