package assignment09;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Jonathan Oliveros & Joshua Wells
 *
 *         Timing Class for Assignment 08
 *
 */

public class Timing {

    /**
     * ArrayList of different numbers of elements to add to heap
     */
    private ArrayList<Integer> heapSize;

    /**
     * Initializes the required variables and tables for use
     */
    @Before
    public void setUp() {
        heapSize = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) { // from 10^1 to 10^6
            heapSize.add((int) (Math.pow(10, i + 1)));
        }
    }

    /**
     * creates a Heap<Integer> object with size number of elements
     *
     * @param size int the number of elements in the resulting heap
     * @return tree Heap<Integer> with size number of elements
     */
    private Heap<Integer> createHeap(int size) {
        Heap<Integer> x = new Heap<Integer>();
        for (int i = 0; i < size; i++) {
            // distinct integer {int i} is added to the hashTable
            x.add(i); // add the integer
        }
        return x;
    }

    /**
     * creates a Heap<Integer> object with size number of elements
     *
     * @param cmp  Comparator<Integer> used to compare elements in the tree
     * @param size int the number of elements in the resulting heap
     * @return tree Heap<Integer> with size number of elements
     */
    @SuppressWarnings("unused")
    private Heap<Integer> createHeap(Comparator<Integer> cmp, int size) {
        Heap<Integer> x = new Heap<>(cmp);
        for (int i = 0; i < size; i++) {
            // distinct integer {int i} is added to the hashTable
            x.add(i); // add the integer
        }
        return x;
    }

    /**
     * test removeFirst on given heap x
     *
     * @param x
     * @return time required to run removeFirst
     */
    private long getRemoveFirstTime(Heap<Integer> x) {
        long start = System.nanoTime();
        x.removeFirst();
        long stop = System.nanoTime();

        return stop - start;
    }

    /**
     * Calculates an average of amount of time to run removeFirst on each size
     *
     * @param x
     * @param times
     * @return Average time of removeFirst
     */
    private long averageTimeRemoveFirst(Heap<Integer> x, long times) {
        long average = 0L;
        Random r = new Random();
        for (int i = 0; i < times; i++) {
            average += getRemoveFirstTime(x);
            x.add(r.nextInt()); // keep the heap the same size
        }
        return average / times;
    }

    /**
     * test removeFirst on given heap x
     *
     * @param x
     * @return time required to run removeFirst
     */
    private long getAddTime(Heap<Integer> x, int i) {
        long start = System.nanoTime();
        x.add(i);
        long stop = System.nanoTime();

        return stop - start;
    }

    /**
     * Calculates an average of amount of time to run removeFirst on each size
     *
     * @param x
     * @param times
     * @return Average time of removeFirst
     */
    private long averageTimeAdd(Heap<Integer> x, long times) {
        long average = 0L;
        Random r = new Random();
        for (int i = 0; i < times; i++) {
            average += getAddTime(x, r.nextInt());
            x.removeFirst(); // keep the heap the same size
        }
        return average / times;
    }

    /**
     * test removeFirst on given heap x
     *
     * @param x
     * @return time required to run removeFirst
     */
    private long getSortTime(Heap<Integer> x) {
        long start = System.nanoTime();
        x.sort();
        long stop = System.nanoTime();

        return stop - start;
    }

    /**
     * Calculates an average of amount of time to run removeFirst on each size
     *
     * @param x
     * @param times
     * @return Average time of removeFirst
     */
    private long averageTimeSort(Heap<Integer> x, long times) {
        long average = 0L;
        for (int i = 0; i < times; i++) {
            average += getSortTime(x);
        }
        return average / times;
    }

    /**
     * Method that tests removeFirst on trees of various sizes.
     */
    @Test
    public void times() {

        // remove first
        System.out.println("\nremoveFirst:");
        for (int num : heapSize) {
            Heap<Integer> x = createHeap(num);
            System.out.println("Remove: The time for " + num
                    + " sized tree with default comparator was: ");
            System.out.println((averageTimeRemoveFirst(x, 1)));
        }

        // add
        System.out.println("\nadd:");
        for (int num : heapSize) {
            Heap<Integer> x = createHeap(num);
            System.out.println("Add: The time for " + num
                    + " sized tree with default comparator was: ");
            System.out.println((averageTimeAdd(x, 1)));
        }

        // sort
        System.out.println("\nsort:");
        for (int num : heapSize) {
            Heap<Integer> x = createHeap(num);
            System.out.println("Sort: The time for " + num
                    + " sized tree with default comparator was: ");
            System.out.println((averageTimeSort(x, 1)));
        }
    }
}