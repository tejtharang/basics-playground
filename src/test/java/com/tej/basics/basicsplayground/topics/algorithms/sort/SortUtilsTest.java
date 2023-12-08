package com.tej.basics.basicsplayground.topics.algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortUtilsTest {

    @Test
    public void mergeSortTest() {
        Integer [] arr = new Integer[] {5,1,9,3,10,7};
        SortUtils.mergeSort(arr, 0, 5);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void quickSortTest() {
        Integer [] arr = new Integer[] {1,0};
        SortUtils.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test3() {
       int x = 8;
        System.out.println(x & x-1);
    }
}
