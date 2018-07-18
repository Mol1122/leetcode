public class Solution {
    /**
     * @param init_state: the initial state of chessboard
     * @param final_state: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    public int minMoveStep(int[][] init_state, int[][] final_state) {
        String source = matrixToString(init_state);
        String target = matrixToString(final_state);
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(source);
        visited.add(source);
        
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curt = queue.poll();
                if (curt.equals(target)) {
                    return step;
                }
                
                for (String next : getNext(curt)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    queue.offer(next);
                    visited.add(next);
                }
            }
            step++;
        }
        
        return -1;
    }
    
    public String matrixToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(state[i][j]);
            }
        }
        return sb.toString();
    }
    
    public List<String> getNext(String state) {
        List<String> states = new ArrayList<>();
        int[] dx = {0, 1, -1, 0};
        int[] dy = {1, 0, 0, -1};
        
        int zeroIndex = state.indexOf('0');
        int x = zeroIndex / 3;
        int y = zeroIndex % 3;
        
        for (int i = 0; i < 4; i++) {
            int x_ = x + dx[i];
            int y_ = y + dy[i];
            if (x_ < 0 || x_ >= 3 || y_ < 0 || y_ >= 3) {
                continue;
            }
            
            char[] chars = state.toCharArray();
            chars[x * 3 + y] = chars[x_ * 3 + y_];
            chars[x_ * 3 + y_] = '0';
            states.add(new String(chars));
        }
        
        return states;
    }
}