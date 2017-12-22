package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static <T extends Comparable<T>> void sort(List<T> list) {
        mergesortWrapper(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void mergesortWrapper(List<T> list,
            int left, int right) {
        if (left < right) {
            int midpoint = (left + right) / 2;

            mergesortWrapper(list, left, midpoint);
            mergesortWrapper(list, midpoint + 1, right);
            merge(list, left, midpoint, right);
        }
    }

    private static <T extends Comparable<T>> void merge(List<T> list, int left,
            int midpoint, int right) {
        List<T> listCopy = new ArrayList<>(list);
        int leftIndex = left;
        int rightIndex = midpoint + 1;
        int mergeIndex = left;
        
        while (leftIndex <= midpoint && rightIndex <= right) {
            if (listCopy.get(leftIndex).compareTo(listCopy.get(rightIndex)) > 0) {
                list.set(mergeIndex, listCopy.get(rightIndex));
                rightIndex++;
            } else {
                list.set(mergeIndex, listCopy.get(leftIndex));
                leftIndex++;
            }
            mergeIndex++;
        }

        for (int i = leftIndex; i <= midpoint; i++) {
            list.set(mergeIndex, listCopy.get(i));
            mergeIndex++;
        }

        for (int i = rightIndex; i <= right; i++) {
            list.set(mergeIndex, listCopy.get(i));
            mergeIndex++;
        }
    }

    public static void main(String[] args) {
        List<Character> list = Arrays.asList('w', 'r', 'b', 'c', 'z', 'a', 't');
        List<Integer> arr2 = Arrays.asList(10, 4, -3, 6, 3, 9, -4, 6);

        MergeSort.sort(list);
        MergeSort.sort(arr2);

        System.out.println(list);
        System.out.println(arr2);
    }
}
