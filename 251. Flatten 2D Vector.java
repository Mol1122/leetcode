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
        for (int num : list) {
            temp.push(num);
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
        //prepare for the next element
        while (elements.isEmpty() && !stack.isEmpty()) {
            pushElementToStack(stack.pop());
        }
        return !elements.isEmpty();
    }
}
/* 算法：利用stack去记录list of list and list of integers */
/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */