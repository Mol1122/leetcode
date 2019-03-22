public static int taskScheduler(String s, int cooldown) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0, time = 0;
        Map<Character, Integer> ch2time = new HashMap<>();
        while (i < s.length()) { //1st task placed at time 1
            char task = s.charAt(i);
            if (ch2time.containsKey(task)) {
                time = Math.max(time + 1, ch2time.get(task) + cooldown + 1);
            } else {
                time++;
            }
            ch2time.put(task, time);
            i++;
        }
        return time;
    }

    public static int taskScheduleFollowUp(String s, int cooldown) {
        int currTime = 0;
        Queue<Character> queue = new LinkedList<>(); //maintain tasks in expiring time
        Map<Character, Integer> ch2time = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            currTime++;
            if (!queue.isEmpty() && ch2time.get(queue.peek()) == currTime) {
                ch2time.remove(queue.peek());
                queue.poll();
            }
            char curt = s.charAt(i);
            if (queue.contains(curt)) {
                while (queue.peek() != curt) {
                    ch2time.remove(queue.peek());
                    queue.poll();
                }
                currTime = ch2time.get(curt);
                queue.poll();
            }
            queue.offer(curt);
            ch2time.put(curt, currTime + cooldown + 1);
        }
        return currTime;
    }
