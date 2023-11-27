package com.tej.basics.basicsplayground.topics.algorithms.search;

import org.springframework.stereotype.Service;

@Service
public class BinarySearchUtils {
    public static <T extends Comparable<T>> boolean binarySearch(T[] arr, T item) {
        if(arr.length == 0)
            throw new IllegalArgumentException("Array is empty!");
        int start = 0;
        int end = arr.length -1;

        while(start < end) {
            int mid = start + (end-start)/2;
            if(arr[mid].equals(item)) {
                return true;
            } else if(arr[mid].compareTo(item) < 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
