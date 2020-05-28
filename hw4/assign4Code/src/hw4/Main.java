package hw4;

import java.lang.management.ManagementFactory;
import java.util.*;
import java.lang.management.ThreadMXBean;

public class Main {

    public static void main(String[] args) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        Sorts sort = new Sorts();
        int length;
        int x = 10;
        String[] mergeTimes = new String[x];
        String[] heapTimes = new String[x];
        String[] quickTimes = new String[x];
        String[] insertTimes = new String[x];


        for(int i = 0; i < x; i++) {
            double avgTimeA = 0;
            long totTimeA = 0;
            double avgTimeB = 0;
            long totTimeB = 0;
            double avgTimeC = 0;
            long totTimeC = 0;
            double avgTimeD = 0;
            long totTimeD = 0;

            length = (int) Math.pow(2, i);

            for(int j = 0; j < 10000; j++) {
                long[] a = createArray(length);
                long[] b = copyArray(a);
                long[] c = copyArray(a);
                long[] d = copyArray(a);

                long startTimeA = bean.getCurrentThreadCpuTime();
                sort.mergeSort(a);
                long endTimeA = bean.getCurrentThreadCpuTime();
                totTimeA = totTimeA + (endTimeA - startTimeA);
                avgTimeA = totTimeA / (j + 1);

                mergeTimes[i] = "Run: " + i + " Time: " + avgTimeA;

                long startTimeB = bean.getCurrentThreadCpuTime();
                sort.heapSort(b);
                long endTimeB = bean.getCurrentThreadCpuTime();
                totTimeB = totTimeB + (endTimeB - startTimeB);
                avgTimeB = totTimeB / (j + 1);

                heapTimes[i] = "Run: " + i + " Time: " + avgTimeB;

                long startTimeC = bean.getCurrentThreadCpuTime();
                sort.insertionSort(c);
                long endTimeC = bean.getCurrentThreadCpuTime();
                totTimeC = totTimeC + (endTimeC - startTimeC);
                avgTimeC = totTimeC / (j + 1);

                insertTimes[i] = "Run: " + i + " Time: " + avgTimeC;

                long startTimeD = bean.getCurrentThreadCpuTime();
                sort.quickSort(d);
                long endTimeD = bean.getCurrentThreadCpuTime();
                totTimeD = totTimeD + (endTimeD - startTimeD);
                avgTimeD = totTimeD / (j + 1);

                quickTimes[i] = "Run: " + i + " Time: " + avgTimeD;
            }
        }

        System.out.println("Merge Sort Times:");
        arrayOutStr(mergeTimes);
        System.out.println();
        System.out.println("Heap Sort Times:");
        arrayOutStr(heapTimes);
        System.out.println();
        System.out.println("Insertion Sort Times:");
        arrayOutStr(insertTimes);
        System.out.println();
        System.out.println("Quick Sort Times:");
        arrayOutStr(quickTimes);
    }

    public static void arrayOut(long[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void arrayOutStr(String[] a) {
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static long[] createArray(int length) {
        Random rand = new Random();
        long[] a = new long[length];

        for(int i = 0; i < length; i++) {
            a[i] = Math.abs(rand.nextLong());
        }

        return a;
    }

    public static long[] copyArray(long[] a) {
        long[] b = new long[a.length];

        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        return b;
    }
}
