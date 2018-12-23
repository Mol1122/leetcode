    /* [['a', 'b', 'c'],
['d', 'e', 'f']]

output:
a->b->c->f
a->b->e->f
a->d->e->f */
    public static void print2DarrayPath(char[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return;
        }
        List<List<Character>> results = new ArrayList<>();
        List<Character> path = new ArrayList<>();
        //path.add(arr[0][0]);
        dfsPath(arr, 0, 0, path, results);

        for (List<Character> list : results) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + "->");
            }
            System.out.println();
        }
    }

    private static void dfsPath(char[][] arr, int row, int col, List<Character> path, List<List<Character>> results) {
        if (row == arr.length - 1 && col == arr[0].length - 1 ) {
            path.add(arr[row][col]);
            results.add(new ArrayList<>(path));
            return;
        }
        path.add(arr[row][col]);
        if (row + 1 < arr.length) {
            dfsPath(arr, row + 1, col, path, results);
            path.remove(path.size() - 1);
        }

        if (col + 1 < arr[0].length) {
            dfsPath(arr, row, col + 1, path, results);
            path.remove(path.size() - 1);
        }
    }