public class ThreeStacks {
    /*
    * @param size: An integer
    */
    int[] threeStacks;
    int[] pos;
    int stackSize;
    public ThreeStacks(int size) {
        stackSize = size;
        pos = new int[3];
        threeStacks = new int[size * 3];
    }

    /*
     * @param stackNum: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void push(int stackNum, int value) {
        threeStacks[stackSize * stackNum + pos[stackNum]] = value;
        pos[stackNum]++;
    }

    /*
     * @param stackNum: An integer
     * @return: the top element
     */
    public int pop(int stackNum) {
        int num = 0;
        if (pos[stackNum] != 0) {
            num = threeStacks[stackSize * stackNum + (pos[stackNum] - 1)];
        }
        pos[stackNum]--;
        return num;
    }

    /*
     * @param stackNum: An integer
     * @return: the top element
     */
    public int peek(int stackNum) {
        int num = 0;
        if (pos[stackNum] != 0) {
            num = threeStacks[stackSize * stackNum + (pos[stackNum] - 1)];
        }
        return num;
    }

    /*
     * @param stackNum: An integer
     * @return: true if the stack is empty else false
     */
    public boolean isEmpty(int stackNum) {
        return pos[stackNum] == 0;
    }
}