package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {

    public static class ModeSearchResult<T> {
        public T theMode;
        public int count = 0;
    }

    /**
     * Simple sorting algorithm
     * @param <T>
     * @param array
     */
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

    /**
     * Reverse elements of array
     * @param <T>
     * @param array
     */
    public static <T> void reverse(T [] array) {
        int i = 0;
        while (i <= (array.length/2)-1) {
            swap(array, i, array.length-i-1);
            i++;
        }
    }

    /**
     * Swap two elements in place
     * @param <T>
     * @param array
     * @param first
     * @param second
     */
    public static <T> void swap(T [] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    /**
     * Find the mode of an array
     * @param <T>
     * @param array
     * @return
     */
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

    /**
     * Partition array by given rule
     * @param <T>
     * @param array
     * @param count
     * @param rule
     * @return
     */
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

    /**
     * Binary search algorithm
     * @param <T>
     * @param aValue
     * @param fromArray
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int middle = fromIndex + (toIndex - fromIndex) / 2;
            if (aValue.compareTo(fromArray[middle]) < 0) {
               toIndex = middle - 1;
            } else if (aValue.compareTo(fromArray[middle]) > 0) {
               fromIndex = middle + 1;
            } else {
               return middle;
            }
         }
         return -1;
    }

    /**
     * Fast sorting algorithm: Heapsort
     * @param <T>
     * @param array
     */
    public static <T extends Comparable<T>> void fastSort(T [] array) {
        int length = array.length;
        heapify(array, length);
        int end = length-1;
        while (end > 0) {
            swap(array, 0, end);
            end--;
            siftDown(array, 0, end);
        }
    }

    private static <T extends Comparable<T>> void heapify(T [] array, int length) {
        int start = parent(length-1);
        while (start >= 0) {
            siftDown(array, start, length-1);
            start--;
        }
    }

    private static <T extends Comparable<T>> void siftDown(T [] array, int start, int end) {
        int root = start;
        while (leftChild(root) <= end) {
            int child = leftChild(root);
            int swap = root;
            if (array[swap].compareTo(array[child]) < 0) {
                swap = child;
            }
            if (child+1 <= end && array[swap].compareTo(array[child+1]) < 0) {
                swap = child+1;
            }
            if (swap == root) {
                break;
            } else {
                swap(array, swap, root);
                root = swap;
            }
        }
    }

    private static int parent(int i) {
        return (int) Math.floor((i-1)/2);
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

}
