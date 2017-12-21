package algorithms;

import java.util.Arrays;

public class QuickSort {
    /**
     * Sorts array in place.
     * 
     * @param arr
     */
    public static <T extends Comparable<T>> void sort(T[] arr) {
        quicksortWrapper(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quicksortWrapper(T[] arr,
            int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quicksortWrapper(arr, left, pivot - 1);
            quicksortWrapper(arr, pivot + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int left,
            int right) {
        int key = randint(left, right);
        swap(arr, left, key);
        T pivot = arr[left];

        int i = left + 1;
        for (int j = i; j < right + 1; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i - 1, left);
        return i - 1;
    }

    private static int randint(int a, int b) {
        return a + (int) (Math.random() * (b - a + 1));
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Character[] arr = { 'c', 'b', 'a', 'z', 't', 'f' };
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
