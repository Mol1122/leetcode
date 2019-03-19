class Solution {
    public int search(ArrayReader reader, int target) {
        if (reader == null) {
            return -1;
        }
        int start = 0, end = 1;
        while (reader.get(end) != Integer.MAX_VALUE && reader.get(end) < target) {
            start = end;
            end = 2 * end;
        }
        return binarySearch(reader, target, start, end);
    }
    
    private int binarySearch(ArrayReader reader, int target, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (target < reader.get(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (reader.get(start) == target) {
            return start;
        } else if (reader.get(end) == target) {
            return end;
        }
        return -1;
    }
}