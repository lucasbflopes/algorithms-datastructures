package algorithms;

public class BinarySearch {

    public static boolean search(double[] array, double value) {
        return searchWrapper(array, 0, array.length - 1, value);
    }

    private static boolean searchWrapper(double[] array, int start, int end,
            double value) {
        if (end == start)
            return false;
        int midpoint = (end + start) / 2;
        if (value < array[midpoint]) {
            return searchWrapper(array, start, midpoint, value);
        } else if (value > array[midpoint]) {
            return searchWrapper(array, midpoint + 1, end, value);
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        double[] array = { 1, 2, 3, 5, 6, 7.2, 8, 9 };
        boolean result = search(array, 2);
        System.out.println(result);
    }
}
