package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {

    public static class ModeSearchResult<T> {
        public T theMode;
        public int count = 0;
    }

    public static <T extends Comparable<T>> void sort(T [] array) {
        for (int i = array.length; i > 0; i--) {
            int j = array.length-1;
            while (j > 0) {
                if (array[j].compareTo(array[j-1]) < 0) {
                    swap(array, j, j-1);
                }
                j--;
            }
        }
    }

    public static <T> void reverse(T [] array) {
        int i = 0;
        while (i <= (array.length/2)-1) {
            swap(array, i, array.length-i-1);
            i++;
        }
    }

    public static <T> void swap(T [] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static <T extends Comparable<T>> ModeSearchResult<T> findMode(T [] array) {
        ModeSearchResult<T> result = new ModeSearchResult<>();
        result.theMode = null;
        result.count = -1;
        int count = 1;

        if (array == null) {
            return result;
        } else if (array.length < 2) {
            return result;
        }

        sort(array);

        for (int i = 1; i < array.length; i++) {
            if (i == array.length-1) {
                if (array[i].compareTo(array[i-1]) == 0) {
                    count++;
                }
                if (count > result.count) {
                    result.theMode = array[i-1];
                    result.count = count;
                    count = 1;
                }
            } else if (array[i].compareTo(array[i-1]) == 0) {
                count++;
            } else if (count > result.count) {
                result.theMode = array[i-1];
                result.count = count;
                count = 1;
            } else {count = 1;}
        }

        return result;
    }

    public static <T> int partitionByRule(T [] array, int count, Predicate<T> rule) {

        int index=0;
        for (int i=0; i<count; i++) {
            if (rule.test(array[i])) {
                break;
            }
            index++;
        }

        if (index >= count) {
            return count;
        }

        int nextIndex = index + 1;
        while (nextIndex < count) {
            if (!rule.test(array[nextIndex])) {
                swap(array, index, nextIndex);
                index++;
            }
            nextIndex++;
        }

        return index;
    }
}
