package com.tej.basics.basicsplayground.topics.concepts.bitManipulation;

public class BitManipulation {

    // important
    public static boolean powerOfTwo(int x) {
        /*
        * power of 2 is calculated as x & (x-1) being 0
        * x - 1 is always going to be every bit after and including the last 1
        *
        * Properties for numbers which are powers of 2, is that they have one and only one bit set in their binary representation.
        * If the number is neither zero nor a power of two, it will have 1 in more than one place. So if x is a power of 2 then x & (x-1) will be 0.
        *
        * */
        return (x & (x-1)) == 0;
    }

    public static boolean isOddNumber(int x) {
        return (x & 1) == 1;
    }

    public static boolean integersHaveOppositeSign(int x , int y) {
        return (x ^ y) < 0;
    }

    // invert bits and add 1 to it
    public static int negativeOfNumber(int x) {
        return ~x + 1;
    }

    // remember two's complement ? It's used to calculate the numbers of the opposite sign
    // here's the link : https://www.cs.cornell.edu/~tomf/notes/cps104/twoscomp.html#:~:text=Two's%20complement%20is%20the%20way,add%20one%20to%20the%20result.

    public static String turnOffKthBit(int x, int k) {
        return Integer.toBinaryString(x & (~(1 << (k-1))));
    }

    public static String turnOnKthBit(int x, int k) {
        return Integer.toBinaryString(x | (~(1 << (k-1))));
    }

    public static boolean checkIfKthBitSet(int x, int k) {
        return (x & (1 << (k-1))) != 0;
    }

    public static int toggleKthBit(int x, int k) {
        return (x ^ (1 << (k-1)));
    }


}
