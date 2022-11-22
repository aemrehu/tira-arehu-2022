package oy.tol.tra;

import javax.lang.model.element.Element;

public class Algorithms {
    
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
}
