/* Follow up Zigzag Iterator: What if you are given k 1d vectors? How well can your code be extended to such cases? 
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, 
replace "Zigzag" with "Cyclic".

Example
Example1

Input: k = 3
vecs = [
    [1,2,3],
    [4,5,6,7],
    [8,9],
]
Output: [1,4,8,2,5,9,3,6,7]
Example2

Input: k = 3
vecs = [
    [1,1,1]
    [2,2,2]
    [3,3,3]
]
Output: [1,2,3,1,2,3,1,2,3] */

public class ZigzagIterator2 {
    List<Iterator<Integer>> its;
    int turn = 0;
    
    public ZigzagIterator2(List<List<Integer>> v) {
        its = new ArrayList<>();
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).size() > 0) {
                its.add(v.get(i).iterator());    
            }
        }
    }

    public int next() {
        int ele = its.get(turn).next();
        if (its.get(turn).hasNext()) {
            turn = (turn + 1) % its.size();
        } else {
            its.remove(turn);
            if (its.size() > 0) {
                turn %= its.size();
            }
        }
        return ele;
    }

    public boolean hasNext() {
        return its.size() > 0;
    }
}

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution = new ZigzagIterator2(vecs);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */