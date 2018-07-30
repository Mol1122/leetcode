public class Solution {
    /**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndex(int[] A) {
        long sum = 0, count;
        if (A == null || A.length == 0) {
            return -1;
        }
        for(int i = 0; i < A.length; i++){
            count = 0;
            for(int j = i + 1; j < A.length; j++){
                if(A[i] > A[j]) {
                    count++;
                }
            }
            sum += count * fac(A.length - i - 1);
        }
        return sum + 1;
    }
    
    private long fac(int num) {
        if (num == 0) {
            return num;
        }
        long product = 1;
        while (num != 0) {
            product *= num;
            num--;
        }
        return product;
    }
}

/* 算法：康拓排序
{1,2,3,4,...,n}表示1,2,3,...,n的排列如 {1,2,3} 按从小到大排列一共6个：123 132 213 231 312 321。他们间的对应关系可由康托展开来找到。
1324是{1,2,3,4}排列数中第几个大的数：
第一位是1小于1的数没有，是0个 0*3!
第二位是3小于3的数有1和2，但1已经在第一位了，即1未出现在前面的低位当中，所以只有一个数2 1*2!
第三位是2小于2的数是1，但1在第一位，即1未出现在前面的低位当中，所以有0个数 0*1!
所以比1324小的排列有0*3!+1*2!+0*1!=2个，1324是第三个大数。
*/