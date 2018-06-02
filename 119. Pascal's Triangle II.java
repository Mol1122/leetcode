class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        rowIndex++;
        if (rowIndex == 0) {
            return result;
        }
        result.add(1);
        for (int i = 1; i < rowIndex; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>(i+1); //has i+1 个element in the next line
            for (int j = 0; j < i + 1; j++) {
                temp.add(-1);
            }
            temp.set(0, result.get(0));
            temp.set(i, result.get(i-1));
            for (int j = 1; j < i; j++) {
                temp.set(j, result.get(j - 1) + result.get(j));
            }
            result = temp;
        }
        return result;
    }
}

/* 语法：ArrayList在initialize的时候需要手动赋值
** 算法：从第一行开始想下generate row
** 难点：index*/