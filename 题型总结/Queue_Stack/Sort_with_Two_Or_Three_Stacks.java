// no duplicates, based on Selection Sort
    //time: O(n^2), space: O(n)
    public Stack<Integer> sortWithTreeStack1(Stack<Integer> input) {
        Stack<Integer> buffer = new Stack<>();
        Stack<Integer> output = new Stack<>();

        int globalMin;

        while (!input.isEmpty()) {
            globalMin = Integer.MAX_VALUE;
            while (!input.isEmpty()) {
                int val = input.pop();
                buffer.push(val);
                globalMin = Math.min(globalMin, val);
            }
            output.push(globalMin);
            while (!buffer.isEmpty()) {
                if (buffer.peek() != globalMin) {
                    input.push(buffer.pop());
                } else {
                    buffer.pop();
                }
            }
        }
        return output;
    }

    //has duplicates, based on Selection Sort
    //time: O(n^2), space: O(n)
    public Stack<Integer> sortWithTreeStack2(Stack<Integer> input) {
            Stack<Integer> buffer = new Stack<>();
            Stack<Integer> output = new Stack<>();

            int globalMin;
            int minCount = 0;

            while (!input.isEmpty()) {
                globalMin = Integer.MAX_VALUE;
                minCount = 0;
                while (!input.isEmpty()) {
                    int val = input.pop();
                    buffer.push(val);
                    if (val < globalMin) {
                        globalMin = val;
                        minCount = 1;
                    } else if (val == globalMin) {
                        minCount++;
                    }
                }
                output.push(globalMin);
                while (!buffer.isEmpty()) {
                    if (buffer.peek() != globalMin) {
                        input.push(buffer.pop());
                    } else {
                        int temp = buffer.pop();
                        minCount--;
                        if (minCount > 0) {
                            input.push(temp);
                        }
                    }
                }
            }

            return output;
    }

    //no duplicates, based on Selection Sort
    //time: O(n^2), space: O(n)
    public Stack<Integer> sortWithTwoStacks1(Stack<Integer> input) {
        Stack<Integer> output = new Stack<>();
        int globalMin;

        while (!input.isEmpty()) {
            globalMin = Integer.MAX_VALUE;

            while (!input.isEmpty()) {
                int temp = input.pop();
                output.push(temp);
                globalMin = Math.min(globalMin, temp);
            }


            while (!output.isEmpty() && output.peek() >= globalMin) {
                if (output.peek() == globalMin) {
                    output.pop();
                } else {
                    input.push(output.pop());
                }
            }
            output.push(globalMin);
        }

        return output;
    }

    //has duplicates, based on Selection Sort
    //time: O(n^2), space: O(n)
    public Stack<Integer> sortWithTwoStacks2(Stack<Integer> input) {
        Stack<Integer> output = new Stack<>();
        int globalMin;
        int minCount = 0;

        while (!input.isEmpty()) {
            globalMin = Integer.MAX_VALUE;
            minCount = 0;

            while (!input.isEmpty()) {
                int temp = input.pop();
                output.push(temp);
                if (temp < globalMin) {
                    globalMin = temp;
                    minCount = 1;
                } else if (temp == globalMin) {
                    minCount++;
                }
            }

            while (!output.isEmpty() && output.peek() >= globalMin) {
                if (output.peek() == globalMin) {
                    output.pop();
                } else {
                    input.push(output.pop());
                }
            }
            for (int i = 0; i < minCount; i++) {
                output.push(globalMin);
            }
        }

        return output;
    }

    //has duplicates, based on Insertion Sort
    //time: O(n^2), space: O(n)
    public Stack<Integer> sortWithTwoStacks3(Stack<Integer> input) {
        Stack<Integer> buffer = new Stack<>();

        while (!input.isEmpty()) {
            int temp = input.pop();
            while (!buffer.isEmpty() && buffer.peek() > temp) {
                input.push(buffer.pop());
            }
            buffer.push(temp);
        }
        return buffer;
    }
/* 算法：idea来自于insertion sort. 假设我curr element是input最后(最top)一个，并且是最小的。那么我需要把buffer里的所有pop出来并且push到
        input里。For every element, I may have to pushback (n-1) elements back to the original stack and fix 1 element in the tempStack.
        And we have n elements
    时间复杂度：O(n^2)
    空间复杂度：O(n)
         */
