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

    public static <T extends Comparable<T>> void quickSort(T [] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T [] arr, int start , int end) {
        if(arr == null) {
            throw new IllegalArgumentException("Input array cannot be null!");
        }
        if(arr.length == 0 || start > end)
            return;

        int i = start;
        int swapIndex = i;
        int pivot = end;

        while(i < pivot) {

            if(arr[i].compareTo(arr[pivot]) < 1 )  {
                T temp = arr[i];
                arr[i] = arr[swapIndex];
                arr[swapIndex] = temp;
                swapIndex++;
            }
            i++;
        }
        T temp = arr[pivot];
        arr[pivot] = arr[swapIndex];
        arr[swapIndex] = temp;

        pivot = swapIndex;
        quickSort(arr, start, pivot - 1);
        quickSort(arr, pivot + 1, end);

    }

    private static <T extends Comparable<T>> void swap(T [] arr, int i, int j) {

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
