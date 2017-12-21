package algorithms;

import java.util.Arrays;

public class InsertionSort {

    /**
     * Sorts array in place.
     * 
     * @param arr
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        for (int j = 0; j < arr.length; j++) {
            T key = arr[j];
            int i = j;
            while (i > 0 && arr[i - 1].compareTo(key) > 0) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[i] = key;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 5, -1, 10, 4, 3, 2, 0, 1, 3 };
        InsertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
