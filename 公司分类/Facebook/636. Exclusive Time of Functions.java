/* On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.

We store logs in timestamp order that describe when a function is entered or exited.

Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  
For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  
"1:end:2" means the function with id 1 ended at the end of timestamp 2.

A function's exclusive time is the number of units of time spent in this function.  
Note that this does not include any recursive calls to child functions.

The CPU is single threaded which means that only one function is being executed at a given time unit.

Return the exclusive time of each function, sorted by their function id.

 

Example 1:



Input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time. 
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing. */

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] results = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int pre_time = 0, running = 0;
        
        for (String log : logs) {
            String[] strs = log.split(":");
            int id = Integer.parseInt(strs[0]);
            boolean isStart = strs[1].equals("start");
            int time = Integer.parseInt(strs[2]);
            
            if (isStart == false) { //把所有的end都加一，这样运算的时候直接加就可以了
                time++;
            }
            results[running] += time - pre_time;
            if (isStart) {
                stack.offerLast(running);
                running = id;
            } else {
                running = stack.pollLast();
            }
            pre_time = time;
        } 
        return results;
    }
}

/* 算法：用pre_time去记录上一次的时间，running记录要更改的id(上一次的id)，这样就可以得到现在时间减去过去时间。记得要更改每次的pre_time
** 难点：用stack去保存所有正在运行着的id(running) */