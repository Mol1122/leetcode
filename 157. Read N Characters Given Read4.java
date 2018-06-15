/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] ans = new char[4];
        int offset = 0;
        
        while (true) {
            int size = read4(ans);
            for (int i = 0; i < size && offset < n; i++) {
                buf[offset++] = ans[i]; 
            }
            if (size == 0 || offset == n) { //易漏，一定要是读完了才算成功
                return offset;
            }
        }
    }
}

/* 算法：利用一个while循环去读取，直到读完
** 时间复杂度： O(n) */