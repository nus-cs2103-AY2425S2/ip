package utils;

import java.util.Arrays;
import java.util.function.Function;

public class ArrayUtils {

    /**
     * Finds the index of the target element in the array.
     *
     * @param arr the array to search
     * @param target the target element to find
     * @param <T> the type of elements in the array
     * @return the index of the target element, or -1 if not found
     */
    public static <T> int find(T[] arr, T target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Prepend an element to the start of the array. This returns a new array entirely.
     * 
     * @param arr the array to prepend to
     * @param element the element to prepend
     * @param <T> the type of elements in the array
     * @return the new array with the element prepended
     */
    public static <T> T[] prepend(T[] arr, T element) {
        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        System.arraycopy(arr, 0, newArr, 1, arr.length);
        newArr[0] = element;
        return newArr;
    }

    /**
     * Maps each element in the array to a new element using the given mapper.
     * This returns a new array entirely.
     *
     * @param arr the array to append to
     * @param mapper the mapper function to apply to each element
     * @param <T> the type of elements in the array
     * @return the new mapped array
     */
    public static <T> T[] map(T[] arr, Function<T, T> mapper) {
        T[] newArr = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = mapper.apply(arr[i]);
        }
        return newArr;
    }

    /**
     * Joins the elements of the array from the specified start index to the end
     * of the array with the given delimiter. The start index is inclusive.
     *
     * @param args the array of strings to join
     * @param delimiter the delimiter to use
     * @param start the start index
     * @return the joined string
     */
    public static String joinFromIndex(String[] args, String delimiter, int start) {
        assert start >= 0 : "Start index must be non-negative";
        assert start < args.length : "Start index must be less than the length of the array";
        return String.join(delimiter, Arrays.copyOfRange(args, start, args.length));
    }

    /**
     * Joins the elements of the array from the specified start index to the
     * specified end index with the given delimiter.
     *
     * @param args the array of strings to join
     * @param delimiter the delimiter to use
     * @param start the start index
     * @param end the end index
     * @return the joined string
     */
    public static String joinFromIndex(String[] args, String delimiter, int start, int end) {
        assert start >= 0 : "Start index must be non-negative";
        assert start < args.length : "Start index must be less than the length of the array";
        assert end >= 0 : "End index must be non-negative";
        assert end <= args.length : "End index must be less than or equal to the length of the array";
        assert start <= end : "Start index must be less than or equal to the end index";
        return String.join(delimiter, Arrays.copyOfRange(args, start, end));
    }

    /**
     * Joins the elements of the array from the index of the target element to
     * the end of the array with the given delimiter. Both indices are
     * exclusive.
     *
     * @param args the array of strings to join
     * @param delimiter the delimiter to use
     * @param target the target element to find
     * @return the joined string
     */
    public static String joinFromFind(String[] args, String delimiter, String target) {
        int index = find(args, target);
        return joinFromIndex(args, delimiter, index + 1, args.length);
    }

    /**
     * Joins the elements of the array from the index of the start target
     * element to the index of the end target element with the given delimiter.
     *
     * @param args the array of strings to join
     * @param delimiter the delimiter to use
     * @param startTarget the start target element to find
     * @param endTarget the end target element to find
     * @return the joined string
     */
    public static String joinFromFind(String[] args, String delimiter, String startTarget, String endTarget) {
        int start = find(args, startTarget);
        int end = find(args, endTarget);
        return joinFromIndex(args, delimiter, start + 1, end);
    }

}
