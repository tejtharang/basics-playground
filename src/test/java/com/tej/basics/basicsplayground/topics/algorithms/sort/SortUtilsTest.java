package com.tej.basics.basicsplayground.topics.algorithms.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortUtilsTest {

    @Test
    public void test() {
        Integer [] arr = new Integer[] {5,1,9,3,10,7};
        SortUtils.mergeSort(arr, 0, 5);
        System.out.println(Arrays.toString(arr));
    }
}
