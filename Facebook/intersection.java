public static Interval intersection(Interval i1, Interval i2) {
        if (i1 == null || i2 == null) {
            return null;
        }

        if (i2.start > i1.end || i1.start > i2.end) {
            return new Interval(-1, -1);
        }
        Interval result = new Interval(Math.max(i1.start, i2.start), Math.min(i1.end, i2.end));
        return result;
}