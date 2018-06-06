class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char c : tasks) {
            count[c - 'A']++;
        }
        Arrays.sort(count);
        int i = 25;
        while (i >= 0 && count[i] == count[25]) {
            i--; //如果数量等同于最多的数量，可以少加一个idle
        }
        return Math.max(tasks.length, (count[25] - 1) * (n + 1) + 25 - i); 
    }
}

/* 算法：有(count[25] - 1)个block, 每个block要有(n+1)个元素才能使间隔为n */