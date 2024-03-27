public class SegmentTree {
    int[] st;

    SegmentTree(int[] arr) {
        int x = (int) (Math.ceil(Math.log(arr.length) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new int[max_size];
        constructST(arr, 0, arr.length - 1, 0);
    }

    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    int constructST(int[] arr, int ss, int se, int si) {
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        int mid = getMid(ss, se);
        st[si] = constructST(arr, ss, mid, si * 2 + 1) +
                constructST(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }

    void printTree(int idx, int start, int end, StringBuilder sb) {
        if (idx >= st.length || st[idx] == Integer.MIN_VALUE)
            return;

        StringBuilder prefix = new StringBuilder();
        prefix.append("  ".repeat(idx));

        printTree(2 * idx + 2, (start + end) / 2 + 1, end, sb);
        sb.append(prefix).append(st[idx]).append("\n");
        printTree(2 * idx + 1, start, (start + end) / 2, sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printTree(0, 0, st.length / 2, sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{4, 5, 1, 5});
        System.out.println(segmentTree);
    }
}
