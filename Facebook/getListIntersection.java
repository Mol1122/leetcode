//Intersection of Intervals
    public static List<Interval> getListIntersection(Interval[] i1, Interval[] i2) {
        List<Interval> results = new ArrayList<>();
        if (i1 == null || i2 == null || i1.length == 0 || i2.length == 0) {
            return results;
        }
        int i = 0, j = 0;
        while (i < i1.length && j < i2.length) {
            Interval temp = intersection(i1[i], i2[j]);
            if (temp.start != -1 && temp.end != -1) {
                results.add(temp);
            }
            if (i1[i].end < i2[j].end) {
                i++;
            } else {
                j++;
            }
        }
        return results;
    }