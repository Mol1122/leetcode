/* Given two 1d vectors, implement an iterator to return their elements alternately.

Example
Example1

Input: v1 = [1, 2] and v2 = [3, 4, 5, 6]
Output: [1, 3, 2, 4, 5, 6]
Explanation: 
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
Example2

Input: v1 = [1, 1, 1, 1] and v2 = [3, 4, 5, 6]
Output: [1, 3, 1, 4, 1, 5, 1, 6] */

public class ZigzagIterator {
    Iterator<Integer> it1;
    Iterator<Integer> it2;
    int turn = 0;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        it1 = v1.iterator();
        it2 = v2.iterator();
        turn = 0;
    }

    public int next() {
        turn++;
        if (turn % 2 == 1 && it1.hasNext() || !it2.hasNext()) {
            return it1.next();
        } else if (turn % 2 == 0 && it2.hasNext() || !it1.hasNext()) {
            return it2.next();
        }
        return -1;
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
}