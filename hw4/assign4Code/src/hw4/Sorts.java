package hw4;

public class Sorts {
    static void insertionSort(long[] a) {
        long key;

        for (int i = 1; i < a.length; i++) {
            key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                a[j+1] = a[j];
                j = j-1;
            }

            a[j+1] = key;
        }
    }

    static void mergeSort(long[] a) {
        mergeSortHelp(a, 0, a.length-1);
    }

    private static void mergeSortHelp(long[] a, int l, int r) {
        if (l < r) {
            int m = (l + r - 1) / 2;

            mergeSortHelp(a, l, m);
            mergeSortHelp(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(long[] a, int l, int m, int r) {
        int x = m - l + 1;
        int y =  r - m;
        long[] L = new long[x + 1];
        long[] R = new long[y + 1];

        for (int i = 0; i < x; i++) {
            L[i] = a[l + i];
        }

        for (int j = 0; j < y; j++) {
            R[j] = a[m + j + 1];
        }

        L[x] = Long.MAX_VALUE;
        R[y] = Long.MAX_VALUE;

        int o = 0;
        int p = 0;

        for (int k = l; k <= r; k++) {
            if ((L[o] <= R[p]) && (o < x) && (p < y)) {
                a[k] = L[o];
                o++;
            }
            else if (o < x && p < y) {
                a[k] = R[p];
                p++;
            }
            else if (o < x) {
                a[k] = L[o];
                o++;
            }
            else {
                a[k] = R[p];
                p++;
            }
        }
    }

    static void heapSort(long[] a) {
        int l = a.length;
        buildMaxHeap(a);

        for (int i = a.length - 1; i > 0; i--) {
            long temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            l--;

            maxHeapify(a, 0, l);
        }
    }

    private static void buildMaxHeap(long[] a) {
        for (int i = a.length / 2; i >= 0; i--) {
            maxHeapify(a, i, a.length);
        }
    }

    private static void maxHeapify(long[] a, int i, int l) {
        int largest = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        if ((left < l) && (a[left] > a[i])) {
            largest = left;
        }

        if ((right < l) && (a[right] > a[largest])) {
            largest = right;
        }

        if (largest != i) {
            long temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;

            maxHeapify(a, largest, l);
        }
    }

    static void quickSort(long[] a) {
        int l = 0;
        int h = a.length-1;

        quickSortHelp(a, l, h);
    }

    private static void quickSortHelp(long[] a, int l, int h) {
        if (l < h) {
            int part = partition(a, l, h);

            quickSortHelp(a, l, part - 1);
            quickSortHelp(a, part + 1, h);
        }
    }

    private static int partition(long[] a, int l, int h) {
        long p = a[h];
        int i = l - 1;

        for (int j = l; j < h; j++) {
            if (a[j] <= p) {
                i++;

                long temp1 = a[i];
                a[i] = a[j];
                a[j] = temp1;
            }
        }
        long temp2 = a[i+1];
        a[i+1] = a[h];
        a[h] = temp2;

        return i + 1;
    }
}
