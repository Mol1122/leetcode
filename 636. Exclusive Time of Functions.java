class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        int pre_time = 0, running = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (String str : logs) {
            String[] arr = str.split(":");
            int id = Integer.parseInt(arr[0]);
            boolean isStart = arr[1].equals("start");
            int time = Integer.parseInt(arr[2]);
            
            if (isStart == false) { //把所有的end都加一，这样运算的时候直接加就可以了
                time++;
            }
            result[running] += (time - pre_time);
            if (isStart) {
                stack.push(running);
                running = id;
            } else {
                running = stack.pop();
            }
            pre_time = time;
        }
        return result;
    }
}

/* 算法：用pre_time去记录上一次的时间，running记录要更改的id(上一次的id)，这样就可以得到现在时间减去过去时间。记得要更改每次的pre_time
** 难点：用stack去保存所有正在运行这的id(running) */