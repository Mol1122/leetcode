public static List<Integer> increasingContainer(List<Integer> container) {
        List<Integer> results = new ArrayList<>();
        if (container == null || container.size() == 0) {
            return results;
        }
        Stack<Integer> stack = new Stack<>();
        while (!container.isEmpty()) {
            int curr = container.get(0);
            if (!stack.isEmpty() && curr > stack.peek()) {
                results.add(stack.pop());
            }
            stack.push(curr);
        }
        while (!stack.isEmpty()) {
            results.add(stack.pop());
        }
        return results;
}