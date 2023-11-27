package com.tej.basics.basicsplayground.topics.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SortUtils {

    public static <T extends Comparable<T>> void mergeSort(T [] arr, int start, int end) {
        if(start >= end)
            return;


        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);

    }

    private static <T extends Comparable<T>> void merge(T [] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = 0;

        T [] subArr = Arrays.copyOfRange(arr, start, end + 1);

        while(i <= mid && j <= end) {
            if(arr[i].compareTo(arr[j]) <= 0) {
                subArr[k++] = arr[i++];
            } else {
                subArr[k++] = arr[j++];
            }
        }

        while(i <= mid) {
            subArr[k++] = arr[i++];
        }

        while(j <= end) {
            subArr[k++] = arr[j++];
        }

        for(int t = start; t <= end; t++) {
            arr[t] = subArr[t - start];
        }
    }
}
