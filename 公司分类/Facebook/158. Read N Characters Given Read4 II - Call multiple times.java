/* Given a file and assume that you can only read the file using a given method read4, 
implement a method read to read n characters. Your method read may be called multiple times.

 

Method read4:

The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.

The return value is the number of actual characters read.

Note that read4() has its own file pointer, much like FILE *fp in C. */

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf); 
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char[] buffer = new char[4];
    int head = 0, tail = 0;
    public int read(char[] buf, int n) {
        int i = 0;
        while (i < n) {
            if (head == tail) {
                head = 0;
                tail = read4(buffer);
                if (head == tail) {
                    break;
                }
            } else {
                while (i < n && head < tail) {
                    buf[i++] = buffer[head++];
                }
            }
        }
        return i;
    }
}
//time: O(n), space: O(1)