/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    char[] buffer = new char[4];
    int head = 0, tail = 0;
    
    public int read(char[] buf, int n) {
        int i = 0; 
        
        while (i < n) {
            if (head == tail) {
                head = 0;
                tail = read4(buffer);
                if (tail == 0) { //nothing to read anymore
                    break;
                }
            }
            while (i < n && head < tail) {
                buf[i++] = buffer[head++];
            }
        }
        return i;
    }
}

/* 算法：每次读string都放入buffer里，用双指针去读buffer到buf上
** 时间复杂度： O(n), 空间复杂度 O(4)  */