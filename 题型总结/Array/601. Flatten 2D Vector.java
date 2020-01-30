/* Implement an iterator to flatten a 2d vector.

Example
Example 1:

Input:[[1,2],[3],[4,5,6]]
Output:[1,2,3,4,5,6]
Example 2:

Input:[[7,9],[5]]
Output:[7,9,5] */

public class Vector2D implements Iterator<Integer> {
    Stack<List<Integer>> stack = new Stack<>();
    Stack<Integer> elements = new Stack<>();
    
    private void pushListToStack(List<List<Integer>> vec2d) {
        Stack<List<Integer>> temp = new Stack<>();
        for (List<Integer> list : vec2d) {
            temp.push(list);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
    
    private void pushElementToStack(List<Integer> list) {
        Stack<Integer> temp = new Stack<>();
        for (int ele : list) {
            temp.push(ele);
        }
        while (!temp.isEmpty()) {
            elements.push(temp.pop());
        }
    }
    
    public Vector2D(List<List<Integer>> vec2d) {
        pushListToStack(vec2d);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return elements.pop();
    }

    @Override
    public boolean hasNext() {
        while (elements.isEmpty() && !stack.isEmpty()) {
            pushElementToStack(stack.pop());
        }
        return !elements.isEmpty();
    }

    @Override
    public void remove() {}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */