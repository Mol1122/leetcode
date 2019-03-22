public static int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int result = 0;

        for (int i = 0; i < height.length; i++) {
            if (height[i] != -1) {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int bottomHeight = height[stack.pop()];
                    if (!stack.isEmpty()) {
                        int leftIndex = stack.peek();
                        int leftHeight = height[leftIndex];
                        result += (Math.min(leftHeight, height[i]) - bottomHeight) * (i - leftIndex - 1);
                    }

                }
                stack.push(i);
            } else {
                stack.clear();
            }
        }
        return result;



//        if (height == null || height.length <= 2) {
//            return 0;
//        }
//        int leftIndex = 0;
//        int leftHeight = 0;
//        int bottomHeight = 0;
//        Stack<Integer> container = new Stack<Integer>();
//        int totalwater = 0;
//        for (int i = 0; i < height.length; i++) {
//            if (height[i] != -1) {
//                while (!container.isEmpty() && height[container.peek()] < height[i]) {
//                    int bottom = container.pop();
//                    if (!container.isEmpty()) {
//                        leftIndex = container.peek();
//                        leftHeight = height[leftIndex];
//                        bottomHeight = height[bottom];
//                        totalwater += (Math.min(leftHeight, height[i]) - bottomHeight) * (i - leftIndex - 1);
//                    }
//                }
//                container.push(i);
//            } else {
//                container.clear();
//            }
//        }
//        return totalwater;
    }