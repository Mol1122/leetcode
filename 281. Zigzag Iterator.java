public class ZigzagIterator {
    Iterator<Integer> it1;
    Iterator<Integer> it2;
    int turn;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.it1 = v1.iterator();
        this.it2 = v2.iterator();
        turn = 0;
    }

    public int next() {
        turn++;
        if ((turn % 2 == 1 && it1.hasNext()) || !it2.hasNext()) {
            return it1.next();
        } else if ((turn % 2 == 0 && it2.hasNext()) || !it1.hasNext()) {
            return it2.next();
        }
        return -1;
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */