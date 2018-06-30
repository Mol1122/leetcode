public class Solution {
    /**
     * @param num: An integer
     * @return: an integer array
     */
    public List<Integer> primeFactorization(int num) {
        List<Integer> factors = new ArrayList<Integer>();

        for (int i = 2; i * i <= num; i++) {
            while (num % i == 0) {
                num = num / i;
                factors.add(i);
            }
        }
        
        if (num != 1) {
            factors.add(num);
        }
        
        return factors;
    }
}


/* 算法：拿到问题，首先就要想计算是如何实现的，这个就是按照求prime的方式一步步求出的
** 难点：i * i <= num */